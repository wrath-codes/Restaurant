package com.wrathcodes.restaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.wrathcodes.restaurant.dao.MenuDAO;
import com.wrathcodes.restaurant.domain.Menu;
import com.wrathcodes.restaurant.domain.Restaurant;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class MenuBean implements Serializable {
	private Menu menu;
	private List<Menu> menus;
	private Restaurant currentRestaurant;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Restaurant getCurrentRestaurant() {
		return currentRestaurant;
	}

	public void setCurrentRestaurant(Restaurant currentRestaurant) {
		this.currentRestaurant = currentRestaurant;
	}

	public void view(ActionEvent event) {
		try {
			menu = (Menu) event.getComponent().getAttributes().get("selectedMenu");

			FacesContext.getCurrentInstance().getExternalContext().redirect("/Restaurant/pages/restaurant/menu.xhtml");

		} catch (IOException e) {
			Messages.addGlobalError("An error occurred while trying to view the menu items");
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void list() {
		try {
			MenuDAO menuDAO = new MenuDAO();
			menus = menuDAO.list(currentRestaurant.getCode());

		} catch (RuntimeException e) {
			Messages.addGlobalError("An error occurred while trying to list the menus");
			e.printStackTrace();
		}
	}

	public void add() {
		menu = new Menu(currentRestaurant);
		menu.setRestaurant(currentRestaurant);
	}

	public void save() {
		try {
			MenuDAO menuDAO = new MenuDAO();
			menuDAO.merge(menu);

			add();
			System.out.println("Restaurant code: " + menu.getRestaurant().getCode());
			list();

			Messages.addGlobalInfo("Menu saved successfully");
		} catch (RuntimeException e) {
			Messages.addGlobalError("An error occurred while trying to save the menu");
			e.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			menu = (Menu) event.getComponent().getAttributes().get("selectedMenu");

			MenuDAO menuDAO = new MenuDAO();
			menuDAO.delete(menu);

			menus = menuDAO.list(currentRestaurant.getCode());

			Messages.addGlobalInfo("Menu removed successfully");
		} catch (RuntimeException e) {
			Messages.addGlobalError("An error occurred while trying to remove the menu");
			e.printStackTrace();
		}
	}

	public void edit(ActionEvent event) {
		menu = (Menu) event.getComponent().getAttributes().get("selectedMenu");
	}

}
