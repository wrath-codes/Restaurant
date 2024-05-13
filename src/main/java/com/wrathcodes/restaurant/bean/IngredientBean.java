package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.wrathcodes.restaurant.dao.IngredientDAO;
import com.wrathcodes.restaurant.domain.Ingredient;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class IngredientBean implements Serializable {
	private Ingredient ingredient;
	private List<Ingredient> ingredients;

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@PostConstruct
	public void list() {
		try {
			IngredientDAO ingredientDAO = new IngredientDAO();
			ingredients = ingredientDAO.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void add() {
		ingredient = new Ingredient();
	}

	public void save() {
		try {
			IngredientDAO ingredientDAO = new IngredientDAO();
			ingredientDAO.merge(ingredient);

			ingredient = new Ingredient();
			ingredients = ingredientDAO.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			ingredient = (Ingredient) event.getComponent().getAttributes().get("ingredientSelected");

			IngredientDAO ingredientDAO = new IngredientDAO();
			ingredientDAO.delete(ingredient);

			ingredients = ingredientDAO.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void edit(ActionEvent event) {
		ingredient = (Ingredient) event.getComponent().getAttributes().get("ingredientSelected");
	}

}
