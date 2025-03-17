package controllers;

/* Import java, javafx */
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.Day;
import model.Exercise;
import controllers.exercises.*;



public class MainProgramController implements Initializable {
	
	@FXML
	private TabPane tabPane;
	
	
	@FXML
	private Tab tabDashboard;
	@FXML
	private SummaryTabController SummaryTabController;
	
	@FXML
	private Tab tabGoals;
	@FXML
	private GoalsTabController GoalsTabController;
	
	
	@FXML
	private Tab tabExercises;
	@FXML
	private ExercisesTabController ExercisesTabController;
	
	
	
	public static ArrayList<Exercise> addedExercises = new ArrayList<Exercise>();
	
	
	public static boolean addExercise(Exercise ex) {
		boolean found = false;
		
		for(int i=0; i<addedExercises.size(); i++) {
			
			if(addedExercises.get(i).getName().equals(ex.getName())) {
				System.out.println("This exercise exists!");
				found = true;
				break;
			}
		}
		
		if(!found) {
			System.out.println("This exercise doesn't exist, so we're going to add it here!");
			addedExercises.add(ex);
		}
		
		return found;
	}
	
	
	
	public static ArrayList<Day> days = new ArrayList<Day>();
	

	
	public static Day getDay(LocalDate date) {
		for (int i = 0; i < days.size(); i++) {
			Day d = days.get(i);

			if (d.getDate().isEqual(date)) {
				
				System.out.println("This Day already existed!");
				return d;
			}
		}

		System.out.println("Day didn't exist, so we are creating one instead!");
		
		Day newDay = new Day(date);
		days.add(newDay);

		
		return newDay;
	}
	
	
    @FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
    	
    	
    	Alert alert = new Alert(AlertType.NONE);
    	alert.setContentText("Are you sure you want to logout?");
    	ButtonType buttonTypeOne = new ButtonType("Yes");
    	ButtonType buttonTypeTwo = new ButtonType("No");
    	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	
    	if (result.get() == buttonTypeOne){
    		
    		reset();
    		
    		
    		Parent loginFXML = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
    		Scene scene = new Scene(loginFXML);
    		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		appStage.setScene(scene);
    		appStage.setTitle("Calorie Tracker");
    		appStage.setWidth(944);
    		appStage.setHeight(600);
    		appStage.show();
    	}
	}
    
    private void reset() {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		
		
		tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
	        System.err.println("changed");
	        
	        System.out.println("New tab name: " + newTab.getText());
	        
	        if(newTab == tabDashboard) {
	        	
	        	SummaryTabController.update();
	        }else if(newTab == tabGoals) {
	        	GoalsTabController.update();
	        }else if(newTab == tabExercises) {
	        	ExercisesTabController.update();
	        }
	    });
		
	}
}
