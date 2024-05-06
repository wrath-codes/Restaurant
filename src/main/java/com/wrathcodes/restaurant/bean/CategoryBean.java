package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.wrathcodes.restaurant.dao.CategoryDAO;
import com.wrathcodes.restaurant.domain.Category;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class CategoryBean implements Serializable {
	private Category category;
	private List<Category> categories;

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

	@PostConstruct
	public void list() {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			categories = categoryDAO.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void add() {
		category = new Category();
	}

	public void save() {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.merge(category);

			category = new Category();
			categories = categoryDAO.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.delete(category);

			categories = categoryDAO.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

}