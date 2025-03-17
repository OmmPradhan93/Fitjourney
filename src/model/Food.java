package model;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Food extends Item {

	
	private boolean template;

	
	private double amount;
	private double carbohydrates;
	private double proteins;
	private double fats;
	
	private double calories;
	private double quantity;

	
	private boolean custom;

	
	private double ogAmount;
	private double ogCalories, ogCarbohydrates, ogProteins, ogFats;

	/**
	 * Constructor which takes quantity value
	 * 
	 * @param name
	 * @param values
	 *            {
	 */
	public Food(String name, double[] values) {
		super(name);

		validateArray(values);
		storeArray(values);
		calculateCalories();

		
		if (values.length == 5) {
			this.setQuantity(values[4]);
		}
	}

	/**
	 * Constructor which accepts the template/custom boolean value
	 * 
	 * @param name
	 * @param values
	 * @param bools
	 */
	public Food(String name, double[] values, boolean[] bools) {
		super(name);

		validateArray(values);
		storeArray(values);
		calculateCalories();
		validateBools(bools);
	}

	private void validateBools(boolean[] bools) {
		
		if (bools.length < 1 && bools.length > 2)
			throw new IllegalArgumentException("template or custom not set");

		System.out.println("Length of bool array is: " + bools.length);

		for (int i = 0; i < bools.length; i++) {
			System.out.println("values of bools: " + bools[i]);
		}

		if (bools.length == 2) {
			this.template = bools[0];
			this.setCustom(bools[1]);
		} else {
			this.template = bools[0];
		}

		System.out.println("Am I a custom food? " + this.getCustom());
	}

	/**
	 * Copy constructor
	 * 
	 * @param name
	 * @param food
	 */
	public Food(Food food) {
		super(food.getName());

		validateFood(food);
		setFoodValues(food);
	}

	private void setFoodValues(Food food) {
		this.amount = food.getAmount();
		this.carbohydrates = food.getCarbohydrates();
		this.proteins = food.getProteins();
		this.fats = food.getFats();

		this.calories = food.getCalories();

		this.ogAmount = food.getOgAmount();
		this.ogCarbohydrates = food.getOgCarbohydrates();
		this.ogProteins = food.getOgProteins();
		this.ogFats = food.getOgFats();
		this.ogCalories = food.getOgCalories();
	}

	/**
	 * Copy constructor & its quantity
	 * 
	 * @param name
	 * @param food
	 */
	public Food(Food food, double quantity) {
		super(food.getName());

		validateFood(food);
		setFoodValues(food);

		this.setQuantity(quantity);
	}

	/**
	 * Called once, upon creation to store the original values of this food
	 * (template)
	 * 
	 * @param values
	 *            
	 */
	private final void storeArray(double[] values) {
		
		ogAmount = values[0];
		ogCarbohydrates = values[1];
		ogProteins = values[2];
		ogFats = values[3];
		ogCalories = (ogCarbohydrates * 4) + (ogProteins * 4) + (ogFats * 9);
	}

	
	private void calculateCalories() {
		this.calories = (this.carbohydrates * 4) + (this.proteins * 4) + (this.fats * 9);
	}

	/**
	 * Validates array checks:{ size == 4, negatives, amount > 0}
	 * 
	 * @param values
	 */
	private void validateArray(double[] values) {

		
		if (values.length != 4 && values.length != 5) {
			throw new IllegalArgumentException("Invalid array size");
		}

		
		for (int i = 0; i < values.length; i++) {
			if (values[i] < 0)
				throw new IllegalArgumentException("Negative array values");
		}

		
		if (values[0] == 0)
			throw new IllegalArgumentException("Amount cannot be 0");

		
		this.amount = values[0];
		this.carbohydrates = values[1];
		this.proteins = values[2];
		this.fats = values[3];
	}

	/**
	 * Validates the food values being passed in and will throw
	 *
	 * 
	 * @param food
	 *            
	 */
	private void validateFood(Food food) {
		
		ArrayList<Double> vals = new ArrayList<Double>();
		vals.addAll(Arrays.asList(food.getAmount(), food.getCarbohydrates(), food.getProteins(), food.getFats()));

		int i, len = vals.size();
		for (i = 0; i < len; i++) {
			
			if (vals.get(i) < 0)
				throw new IllegalArgumentException("Negative array values");
		}

		
		if (food.getAmount() == 0)
			throw new IllegalArgumentException("Amount cannot be 0");

		
		double sum = (food.getCarbohydrates() * 4) + (food.getProteins() * 4) + (food.getFats() * 9);

		if (sum != food.getCalories())
			throw new IllegalArgumentException("Invalid calories given for macros");

		
		if (food.getTemplate()) {
			this.template = false;
		}

		
		if (food.getCustom()) {
			this.custom = true;
		}
	}

	/**
	 * Changes the current foods values and multiplies those values by quantity
	 * 
	 * @param changedFood
	 *            
	 * @param quantity
	 *            
	 */
	public void setFood(Food changedFood, double quantity) {
		validateFood(changedFood);
		
		setFoodValues(changedFood);
		
		this.setQuantity(quantity);
	}

	/**
	 * Used to retrieve the original values of this Food (when created) in string
	 * representation Mainly used when trying to edit the values of food and
	 * TextFields require strings
	 * 
	 * @return
	 */
	public String[] getStrValues() {

		String[] vals = { this.name, Double.toString(this.ogAmount), Double.toString(this.ogCarbohydrates),
				Double.toString(this.ogProteins), Double.toString(this.ogFats) };

		return vals;
	}

	public boolean getTemplate() {
		return this.template;
	}

	public void setTemplate(boolean val) {
		this.template = val;
	}

	public double getOgAmount() {
		return ogAmount;
	}

	public double getOgCalories() {
		return ogCalories;
	}

	public double getOgProteins() {
		return ogProteins;
	}

	public double getOgCarbohydrates() {
		return ogCarbohydrates;
	}

	public double getOgFats() {
		return ogFats;
	}

	public double getCalories() {
		return this.calories;
	}

	public void setQuantity(double quantity) {
		
		if (!this.template) {
			this.quantity = quantity;
			
			this.amount = ogAmount * quantity;
			this.calories = ogCalories * quantity;
			this.carbohydrates = ogCarbohydrates * quantity;
			this.fats = ogFats * quantity;
			this.proteins = ogProteins * quantity;
		} else {
			System.out.println("You tried to edit a template food!");
		}
	}

	public double getQuantity() {
		
		if (this.quantity <= 0)
			this.quantity = 1;

		return this.quantity;
	}

	public boolean getCustom() {
		return this.custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
		
		this.setName(this.getName() + " (custom) ");
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public double getFats() {
		return fats;
	}

	public void setFats(double fats) {
		this.fats = fats;
	}

	
	public StringProperty getStrName() {
		return new SimpleStringProperty(this.getName());
	}

	public StringProperty getStrAmount() {
		return new SimpleStringProperty(Double.toString(amount));
	}

	public StringProperty getStrCalories() {
		return new SimpleStringProperty(Double.toString(Helper.round(calories, 2)));
	}

	public StringProperty getStrCarbs() {
		return new SimpleStringProperty(Double.toString(Helper.round(carbohydrates, 2)));
	}

	public StringProperty getStrFats() {
		return new SimpleStringProperty(Double.toString(Helper.round(fats, 2)));
	}

	public StringProperty getStrProts() {
		return new SimpleStringProperty(Double.toString(Helper.round(proteins, 2)));
	}

	public StringProperty getStrQuantity() {
		return new SimpleStringProperty(Double.toString(quantity));
	}
}
