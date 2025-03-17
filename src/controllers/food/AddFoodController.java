package controllers.food;

import java.io.IOException;
/* Import java, javafx, mainPackage */
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Food;

public class AddFoodController extends BaseFoodController implements Initializable {
	
	/**********************************************************
	 * TABLE GUI
	 **********************************************************/
	@FXML
	private TableView<Food> tableviewFoods;
	
	@FXML
	private TableColumn<Food, String> foodColumn, 
	amountColumn, caloriesColumn, carbsColumn, fatsColumn, proteinColumn;

	@FXML
	private TextField textfieldSearch;

	@FXML
	private Button buttonSearch, buttonAddFood, buttonCreateCustom, buttonDelete;

	@FXML
	private Spinner<Double> spinnerQuantity;
	
	
	private Food returnFoodData;
	
	public static ObservableList<Food> foodData = FXCollections.observableArrayList();
	public static ArrayList<Food> addedFoods = new ArrayList<Food>();
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(addedFoods.size() == 0) {
			System.out.println("ADDING WHOLE MILK...");
			
			Food f1 = new Food("Whole Milk", new double[] { 100, 4.70, 3.50, 3.70}, new boolean[] {true});
			Food f2 = new Food("Protein Powder", new double[] {30, 3.77, 23.71, 0.2  }, new boolean[] {true});
			Food f3 = new Food("White Rice", new double[] { 100, 78.90, 6.70, 0.70 }, new boolean[] {true});
			Food f4 = new Food("Semi Skimmed Milk", new double[] {100, 4.80, 3.60, 1.80}, new boolean[] {true});
			Food f5 = new Food("Paneer Tikka", new double[]{150, 12.0, 9.0, 7.0}, new boolean[]{true});
			Food f6 = new Food("Chicken Curry", new double[]{200, 15.0, 20.0, 8.0}, new boolean[]{true});
			Food f7 = new Food("Dal Makhani", new double[]{150, 18.0, 11.0, 9.0}, new boolean[]{true});
			Food f8 = new Food("Butter Naan", new double[]{120, 26.0, 3.0, 2.5}, new boolean[]{true});
			Food f9 = new Food("Vegetable Biryani", new double[]{200, 38.0, 4.0, 5.0}, new boolean[]{true});
			Food f10 = new Food("Chicken Biryani", new double[]{250, 45.0, 20.0, 10.0}, new boolean[]{true});
			Food f11 = new Food("Aloo Paratha", new double[]{180, 32.0, 5.0, 7.0}, new boolean[]{true});
			Food f12 = new Food("Pav Bhaji", new double[]{200, 25.0, 6.0, 9.0}, new boolean[]{true});
			Food f13 = new Food("Masala Dosa", new double[]{300, 36.0, 7.0, 8.0}, new boolean[]{true});
			Food f14 = new Food("Idli", new double[]{100, 22.0, 2.0, 1.5}, new boolean[]{true});
			Food f15 = new Food("Sambar", new double[]{120, 15.0, 3.0, 6.0}, new boolean[]{true});
			Food f16 = new Food("Upma", new double[]{200, 30.0, 5.0, 6.0}, new boolean[]{true});
			Food f17 = new Food("Rasgulla", new double[]{50, 20.0, 1.0, 0.5}, new boolean[]{true});
			Food f18 = new Food("Gulab Jamun", new double[]{150, 35.0, 4.0, 6.0}, new boolean[]{true});
			Food f19 = new Food("Kheer", new double[]{200, 25.0, 6.0, 8.0}, new boolean[]{true});
			Food f20 = new Food("Bhindi Masala", new double[]{150, 9.0, 4.0, 6.0}, new boolean[]{true});
			Food f21 = new Food("Rajma", new double[]{150, 22.0, 9.0, 8.0}, new boolean[]{true});
			Food f22 = new Food("Chole Bhature", new double[]{400, 55.0, 12.0, 18.0}, new boolean[]{true});
			Food f23 = new Food("Palak Paneer", new double[]{200, 12.0, 10.0, 9.0}, new boolean[]{true});
			Food f24 = new Food("Malai Kofta", new double[]{250, 18.0, 10.0, 15.0}, new boolean[]{true});
			Food f25 = new Food("Kachori", new double[]{150, 28.0, 4.0, 7.0}, new boolean[]{true});
			Food f26 = new Food("Poha", new double[]{150, 27.0, 3.0, 5.0}, new boolean[]{true});
			Food f27 = new Food("Dhokla", new double[]{100, 20.0, 2.0, 1.0}, new boolean[]{true});
			Food f28 = new Food("Pani Puri", new double[]{100, 22.0, 3.0, 1.5}, new boolean[]{true});
			Food f29 = new Food("Samosa", new double[]{150, 25.0, 4.0, 8.0}, new boolean[]{true});
			Food f30 = new Food("Lassi", new double[]{200, 15.0, 5.0, 6.0}, new boolean[]{true});
		
			addedFoods.addAll(Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, 
			f17, f18, f19, f20, f21, f22, f23, f24, f25, f26, f27, f28, f29, f30));
	
			
	
			
			for (int i = 0; i < addedFoods.size(); i++) {
				foodData.add(addedFoods.get(i));
			}
		}

		
		foodColumn.setCellValueFactory(cellData -> cellData.getValue().getStrName());

		amountColumn.setCellValueFactory(cellData -> cellData.getValue().getStrAmount());
		caloriesColumn.setCellValueFactory(cellData -> cellData.getValue().getStrCalories());

		carbsColumn.setCellValueFactory(cellData -> cellData.getValue().getStrCarbs());
		fatsColumn.setCellValueFactory(cellData -> cellData.getValue().getStrFats());
		proteinColumn.setCellValueFactory(cellData -> cellData.getValue().getStrProts());

		FilteredList<Food> flFoods = new FilteredList<Food>(foodData, p -> true);

		
		tableviewFoods.setItems(flFoods);
		
		
		
		tableviewFoods.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        if(newSelection.getCustom()) {
		        	buttonDelete.setDisable(false);
		        	buttonDelete.setVisible(true);
		        }else {
		        	buttonDelete.setDisable(true);
		        	buttonDelete.setVisible(false);
		        }
		    }
		});
		
		
		
		
		
		
		

		
		textfieldSearch.setPromptText("Search here!");
		textfieldSearch.setOnKeyReleased(keyEvent -> {
			System.out.println("textfield search activated");
			flFoods.setPredicate(
					p -> p.getName().toLowerCase().contains(textfieldSearch.getText().toLowerCase().trim()));
		});

		
		spinnerQuantity.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100));

		

	}
	
	@FXML
	protected void handleDelete(ActionEvent event) throws IOException {
		
		
		if(buttonDelete.isVisible()) {
			
			try {
				Food selectedFood = tableviewFoods.getSelectionModel().getSelectedItem();
				
				
				if(selectedFood.getCustom()) {
					
					addedFoods.remove(selectedFood);
					foodData.remove(selectedFood);

					update();
				}
				
			}catch(NullPointerException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void update() {
		tableviewFoods.refresh();
	}

	@FXML
	protected void handleAddFood(ActionEvent event) throws IOException {
		System.out.println("Add the food to the table behind us!");
		try {
			Food selectedFood = tableviewFoods.getSelectionModel().getSelectedItem();
			System.out.println("We want to add: " + selectedFood.getName());
			System.out.println("The quantity to add is: " + spinnerQuantity.getValue());
			
			
			returnFoodData = new Food(selectedFood);
			
			buttonAddFood.getScene().getWindow().hide();
		} catch (NullPointerException e) {
			System.out.println("A table row wasn't selected");
		}
	}
	
	public Double getQuantity() {
		return spinnerQuantity.getValue();
	}

	public Food getFood() {
		return returnFoodData;
	}
}