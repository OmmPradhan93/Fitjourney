package controllers.exercises;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Exercise;

public class CustomExerciseController extends BaseExerciseController implements Initializable{
	
	@FXML 
	Button btnCreate;
	@FXML
	CheckBox cbAddToToday;
	
	private Exercise customExercise;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
	@FXML
	protected void handleAddToToday(ActionEvent event) throws IOException {
		tfSets.setDisable(!cbAddToToday.isSelected());
		tfReps.setDisable(!cbAddToToday.isSelected());
		tfWeight.setDisable(!cbAddToToday.isSelected());
		tfCaloriesBurned.setDisable(!cbAddToToday.isSelected());
	}

	public Exercise getExercise() {
		return customExercise;
	}

	public boolean addToTable() {
		return cbAddToToday.isSelected();
	}
	
	@FXML
	protected void handleCreate(ActionEvent event) throws IOException {
		
		if(!tfName.getText().isEmpty() && !cbAddToToday.isSelected()) {
			customExercise = new Exercise(tfName.getText());
			btnCreate.getScene().getWindow().hide();	
		}
		
		else if(!tfName.getText().isEmpty() && cbAddToToday.isSelected()) {
			
			
			customExercise = new Exercise(tfName.getText());
			
			
			try {
				
				customExercise = createExercise(customExercise);
				
			}catch(NumberFormatException e) {
				System.out.println("Cannot convert textfield text to numbers");
				
				
				customExercise = null;
				return;
			}
			
			
			btnCreate.getScene().getWindow().hide();	
		}else {
			
			customExercise = null;
		}
	}

}
