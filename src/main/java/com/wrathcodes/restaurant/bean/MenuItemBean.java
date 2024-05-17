package com.wrathcodes.restaurant.bean;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.TabChangeEvent;

import com.wrathcodes.restaurant.dao.CategoryDAO;
import com.wrathcodes.restaurant.dao.MenuItemDAO;
import com.wrathcodes.restaurant.domain.Category;
import com.wrathcodes.restaurant.domain.Menu;
import com.wrathcodes.restaurant.domain.MenuItem;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class MenuItemBean implements Serializable {
	private MenuItem item;
	private List<MenuItem> items;
	private Menu menu;
	private Category category;

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}


	@PostConstruct
	public void list() {
		try {
			MenuItemDAO itemDAO = new MenuItemDAO();
			items = itemDAO.list(menu.getCode());
			System.out.println("Restaurant: " + menu.getRestaurant().getName());
			System.out.println("Menu: " + menu.getName());
			System.out.println("Dishes Total: " + items.size());
			System.out.println("Dishes: ");
			System.out.println(items);
			System.out.println("╭────────────────────────────╮");
			for (MenuItem item : items) {
				System.out.println("\n" +
                    "│ Code: " + item.getCode() + "\n" +
                    "│ Name: " + item.getName() + "\n" +
                    "│ Description: " + item.getDescription() + "\n" +
                    "│ Price: " + item.getPrice() + "\n" +
                    "│ Available: " + item.getAvailable() + "\n" +
                    "│ Kitchen: " + item.getKitchen() + "\n" +
//                    "│ Menu: " + item.getMenu().getName() + "\n" +
                    "│ Category: " + item.getCategory() );
//					"");
			}
			System.out.println("╰────────────────────────────╯");
		} catch (RuntimeException e) {
			Messages.addGlobalError("An error occurred while trying to list the menu items");
			e.printStackTrace();
		}
	}
	
	public void updateCategory(TabChangeEvent<?> event) {
		CategoryDAO categoryDAO = new CategoryDAO();
		category = categoryDAO.search(event.getTab().getTitle(), menu.getCode());
	}

	public void list(Long categoryCode) {
		try {
			MenuItemDAO itemDAO = new MenuItemDAO();
			items = itemDAO.list(menu.getCode(), categoryCode);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void add() {
		item = new MenuItem();
		item.setMenu(menu);
		item.setCategory(category);
	}

	public void save() {
		try {
			MenuItemDAO itemDAO = new MenuItemDAO();
			itemDAO.merge(item);
			add();

			System.out.println("Menu: " + menu.getName());

			list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			item = (MenuItem) event.getComponent().getAttributes().get("selectedMenuItem");

			MenuItemDAO itemDAO = new MenuItemDAO();
			itemDAO.delete(item);

			list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void edit(ActionEvent event) {
		item = (MenuItem) event.getComponent().getAttributes().get("selectedMenuItem");
	}

}
