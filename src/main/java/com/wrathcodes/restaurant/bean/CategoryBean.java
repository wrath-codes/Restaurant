package com.wrathcodes.restaurant.bean;

import org.omnifaces.util.Messages;
import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.wrathcodes.restaurant.dao.CategoryDAO;
import com.wrathcodes.restaurant.dao.MenuItemDAO;
import com.wrathcodes.restaurant.domain.Category;
import com.wrathcodes.restaurant.domain.Menu;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class CategoryBean implements Serializable {
	private Category category;
	private List<Category> categories;
	private Menu menu;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenu() {
		return menu;
	}

	public void create_default_category() {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.list(menu.getCode());
		if (categories.size() == 0) {
			category = new Category();
			category.setName("Default");
			category.setDescription("Default Description");
			category.setMenu(menu);
			categoryDAO.merge(category);
			categoryDAO.list(menu.getCode());
		}
	}

	public void delete_default_category() {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.list(menu.getCode());
		Category default_category = categoryDAO.search("Default", menu.getCode());

		if (categories.size() > 1 && default_category != null) {
			categoryDAO.delete(default_category);
		} 
	}

	@PostConstruct
	public void list() {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			delete_default_category();
			categories = categoryDAO.list(menu.getCode());
			System.out.println("Categories at list in Bean: " + categories);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void add() {
		category = new Category();
		category.setMenu(menu);
	}

	public void save() {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.merge(category);
			

			list();
			add();
			category = categoryDAO.search(category.getName(), menu.getCode());
			Messages.addGlobalInfo("Category saved successfully");
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Error saving category");
		}
	}

	public void delete(ActionEvent event) {
		try {
			category = (Category) event.getComponent().getAttributes().get("selectedCategory");
			CategoryDAO categoryDAO = new CategoryDAO();
			MenuItemDAO menuItemDAO = new MenuItemDAO();
			menuItemDAO.delete(category.getCode());
			categoryDAO.delete(category);

			Messages.addGlobalInfo("Category removed successfully");
			list();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Error removing category");
		}
	}

	public void edit(ActionEvent event) {
		category = (Category) event.getComponent().getAttributes().get("selectedCategory");
		System.out.println("Category at Edit in Bean: " + category.getName() + " - " + category.getDescription());
	}
}