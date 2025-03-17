package controllers.exercises;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Exercise;
import model.Helper;
import model.Person;

public class AddExerciseController extends BaseExerciseController implements Initializable {
	
	@FXML
	TableView<Exercise> tvExercises;
	
	@FXML
	TableColumn<Exercise, String> tcExercise;
	
	@FXML
	Button btnAddExercise, btnSearch;
	
	@FXML
	TextField tfSearch;
	
	@FXML
	CheckBox checkBoxEstimate;

	
	private Exercise returnExerciseData;
	
	public static ObservableList<Exercise> exerciseData = FXCollections.observableArrayList();
	
	public static ArrayList<Exercise> addedExercises = new ArrayList<Exercise>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		if (addedExercises.size() == 0) {
			Exercise e1 = new Exercise("Bench Press");
			Exercise e2 = new Exercise("Squat");
			Exercise e3 = new Exercise("Deadlift");
			Exercise e4 = new Exercise("Overhead Press");
			Exercise e5 = new Exercise("Barbell Row");
			Exercise e6 = new Exercise("Surya Namaskar (Sun Salutation)");
			Exercise e7 = new Exercise("Kapabhati Pranayama (Breathing Exercise)");
			Exercise e8 = new Exercise("Vrikshasana (Tree Pose)");
			Exercise e9 = new Exercise("Bhujangasana (Cobra Pose)");
			Exercise e10 = new Exercise("Dhanurasana (Bow Pose)");
			Exercise e11 = new Exercise("Naukasana (Boat Pose)");
			Exercise e12 = new Exercise("Push-Ups");
			Exercise e13 = new Exercise("Pull-Ups");
			Exercise e15 = new Exercise("Lunges");
			Exercise e16 = new Exercise("Burpees");
			Exercise e17 = new Exercise("Kettlebell Swings");
			Exercise e18 = new Exercise("Plank");
			Exercise e19 = new Exercise("Side Plank");
			Exercise e20 = new Exercise("Jump Squats");
			Exercise e21 = new Exercise("High Knees");
			Exercise e22 = new Exercise("Skipping Rope (Jump Rope)");
			Exercise e24 = new Exercise("Walking (Brisk Walk)");
			Exercise e26 = new Exercise("Swimming");
			Exercise e28 = new Exercise("Kalaripayattu (Martial Arts Movements)");
		
			addedExercises.addAll(Arrays.asList(
				e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e15, 
				e16, e17, e18, e19, e20, e21, e22, e24, e26, e28
			));
		
			exerciseData.addAll(Arrays.asList(
				e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e15, 
				e16, e17, e18, e19, e20, e21, e22, e24, e26, e28
			));
		}
		
		
		
		FilteredList<Exercise> flExercises = new FilteredList<Exercise>(exerciseData, p -> true);
		
		
		tcExercise.setCellValueFactory(e -> e.getValue().getStrExercise());
		
		
		tvExercises.setItems(flExercises);
		
		
		
		tfSearch.setPromptText("Search here!");
		tfSearch.setOnKeyReleased(keyEvent -> {
			System.out.println("textfield search activated");
			flExercises.setPredicate(
					p -> p.getName().toLowerCase().contains(tfSearch.getText().toLowerCase().trim()));
		});
	}

	public Exercise getExercise() {
		return returnExerciseData;
	}
	
	@FXML
	protected void handleEstimateCaloriesBurned(ActionEvent event) throws IOException {
		
		
		
		if(checkBoxEstimate.isSelected()) {
			tfCaloriesBurned.setDisable(true);
			
			tfCaloriesBurned.setText(calculateCaloriesBurned());
			
		}else {
			tfCaloriesBurned.setDisable(false);
		}
		
	}
	
	/**
	 * This method attempts to estimate the calories burned during exercise
	 * It checks the persons weight(in lbs) and multiplies by the amount of minutes exercised
	 * It then multiplies this by the intensityLevel (which is calculated from the amount of weight lifted)
	 * @return
	 */
	private String calculateCaloriesBurned() {
		
		double weightLifted = 0, sets = 0, reps = 0;
		double timeExercised = 0, intensityLevel = 0, secondsPerRep = 10;
		
		
		try {
			weightLifted = Double.parseDouble(tfWeight.getText());
			sets = Double.parseDouble(tfSets.getText());
			reps = Double.parseDouble(tfReps.getText());
		}catch(NumberFormatException e) {
			System.out.println("TextFields cannot be converted to a double");
		}

		
		
		double personWeight = 0.0;
		if(Person.getInstance().getUnits().equals(("Metric"))){
			personWeight = Person.getInstance().getWeight() * 2.20462;
		}else {
			personWeight = Person.getInstance().getWeight();
			
			
			weightLifted = weightLifted / 2.20462;
		}
		
		
		
		if(weightLifted >=0 && weightLifted <= 20) {
			intensityLevel = 0.0475;
		}else if(weightLifted > 20 && weightLifted <= 40) {
			intensityLevel = 0.0525;
		}else if(weightLifted > 40 && weightLifted <= 80) {
			intensityLevel = 0.055;
		}else if(weightLifted > 80 && weightLifted <= 120) {
			intensityLevel = 0.06;
		}else if(weightLifted > 120 && weightLifted <= 200) {
			intensityLevel = 0.07;
		}
		

		
		timeExercised = (((reps * secondsPerRep) * sets) / 60);
		
		
		double caloriesBurned = (timeExercised * personWeight) * intensityLevel;
		
		
		return Double.toString(Helper.round(caloriesBurned, 2));
	}
	
	/**
	 * FXML TextField handler to check whenever we type into the TextFields: Sets, Reps, Weight
	 * so that we can adjust the calories burned estimation
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleTextField(KeyEvent event) throws IOException {
		
		if(checkBoxEstimate.isSelected()) {
			tfCaloriesBurned.setText(calculateCaloriesBurned());
		}
	}
	
	
	@FXML
	protected void handleAddExercise(ActionEvent event) throws IOException {
		System.out.println("Add the exercise to the table behind us!");
		try {
			Exercise selectedExercise = tvExercises.getSelectionModel().getSelectedItem();
			
			
			Exercise newEx = super.createExercise(selectedExercise);
			
			returnExerciseData = newEx;
	
			btnAddExercise.getScene().getWindow().hide();
		} catch (NullPointerException e) {
			System.out.println("A table row wasn't selected");
		} catch(NumberFormatException e) {
			System.out.println("Errors in converting textfields to numbers");
		}
	}

}
