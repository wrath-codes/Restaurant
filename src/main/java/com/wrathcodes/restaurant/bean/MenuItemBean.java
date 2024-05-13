package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.wrathcodes.restaurant.dao.MenuItemDAO;
import com.wrathcodes.restaurant.domain.Menu;
import com.wrathcodes.restaurant.domain.MenuItem;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class MenuItemBean implements Serializable {
	private MenuItem item;
	private List<MenuItem> items;
	private Menu menu;

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

	@PostConstruct
	public void list() {
		try {
			MenuItemDAO itemDAO = new MenuItemDAO();
			items = itemDAO.list(menu.getCode());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
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
