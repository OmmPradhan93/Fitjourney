package controllers.exercises;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Exercise;

public class BaseExerciseController {
	@FXML
	TextField tfName, tfSets, tfReps, tfWeight, tfCaloriesBurned;
	
	protected Exercise createExercise(Exercise ex) {
		
		Number[] nums = new Number[] {
				Integer.parseInt(tfReps.getText()),
				Integer.parseInt(tfSets.getText()),
				Double.parseDouble(tfWeight.getText()),
				Double.parseDouble(tfCaloriesBurned.getText())		
				};
		
		return new Exercise(ex.getName(), nums);
	}
}
