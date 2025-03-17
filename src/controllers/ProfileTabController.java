package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Person;

public class ProfileTabController extends BaseController implements Initializable {

	
	@FXML
	TextField tfFirstName, tfLastName, tfAge, tfHeight, tfWeight, tfBodyfat, tfWaist;
	@FXML
	ChoiceBox<String> cbGender;
	@FXML
	Button btnEditProfile, btnMetric, btnImperial;
	@FXML
	ImageView imageViewGender;

	
	private ArrayList<Node> refTF = new ArrayList<Node>();
	
	private String units = "Metric";
	
	private boolean editMode = false;
	
	private Image maleGender, femaleGender;

	
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		person = Person.getInstance();

		
		loadGenderImages();

		
		tfFirstName.setText(person.getFirstName());
		tfLastName.setText(person.getLastName());
		tfAge.setText(Integer.toString(person.getAge()));
		tfWeight.setText(Double.toString(person.getWeight()));
		tfHeight.setText(Double.toString(person.getHeight()));
		tfBodyfat.setText(Double.toString(person.getBodyfat()));
		tfWaist.setText(Double.toString(person.getWaist()));

		cbGender.setValue(person.getGender());

		
		refTF.addAll(Arrays.asList(tfAge, tfFirstName, tfLastName, tfAge, tfHeight, tfWeight, tfBodyfat, tfWaist,
				btnMetric, btnImperial, cbGender));

		
		disableTF(true);

		
		updateButtonUnderline();

		
		setupChoiceBox();
	}

	
	private void setupChoiceBox() {
		cbGender.setItems(FXCollections.observableArrayList("Male", "Female"));

		
		ChangeListener<String> changeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					person.setGender(newValue);

					
					updateImageGender(newValue);
				}
			}
		};
		
		cbGender.getSelectionModel().selectedItemProperty().addListener(changeListener);
	}

	
	private void loadGenderImages() {
		try {
			maleGender = new Image("file:media/malech.jpeg");
			femaleGender = new Image("file:media/femalech.jpg");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method which updates the imageViewGender FXML component with either
	 * female/male image
	 * 
	 * @param value
	 *            
	 */
	private void updateImageGender(String value) {
		if (value.equals("Male")) {
			imageViewGender.setImage(maleGender);
		} else if (value.equals("Female")) {
			imageViewGender.setImage(femaleGender);
		}
	}

	
	private void updateButtonUnderline() {
		
		if (units.equals("Metric")) {
			btnMetric.setUnderline(true);
			btnImperial.setUnderline(false);
		} else {
			btnMetric.setUnderline(false);
			btnImperial.setUnderline(true);
		}
	}

	/**
	 * Method to disable or enable TextFields
	 * 
	 * @param value
	 *            
	 */
	private void disableTF(boolean value) {
		
		for (int i = 0; i < refTF.size(); i++) {
			refTF.get(i).setDisable(value);
		}
	}

	/**
	 * FXML button handler for editing the profile values
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleEditProfile(ActionEvent event) throws IOException {

		
		editMode = !editMode;

		
		if (editMode) {

			System.out.println("Edit mode activated");

			btnEditProfile.setText("Save Changes");

			
			disableTF(false);
		} else {
			
			person.setUnits(units);
			person.setFirstName(tfFirstName.getText());
			person.setLastName(tfLastName.getText());
			person.setAge(Integer.parseInt(tfAge.getText()));
			person.setWeight(Double.parseDouble(tfWeight.getText()));
			person.setHeight(Double.parseDouble(tfHeight.getText()));
			person.setGender(cbGender.getValue());
			person.setWaist(Double.parseDouble(tfWaist.getText()));
			person.setBodyfat(Double.parseDouble(tfBodyfat.getText()));

			btnEditProfile.setText("Edit Profile");

			
			disableTF(true);
		}
	}

	/**
	 * FXML button handler for changing to Metric units
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleMetric(ActionEvent event) throws IOException {

		
		if (units.equals("Imperial")) {
			double metricWeight = Double.parseDouble(tfWeight.getText()) / 2.20462;
			tfWeight.setText(Double.toString(metricWeight));

			double metricHeight = Double.parseDouble(tfHeight.getText()) / 0.0328084;
			tfHeight.setText(Double.toString(metricHeight));

			double metricWaist = Double.parseDouble(tfWaist.getText()) / 0.393701;
			tfWaist.setText(Double.toString(metricWaist));
		}

		units = "Metric";
		updateButtonUnderline();
	}

	/**
	 * FXML button handler for changing to Imperial units
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void handleImperial(ActionEvent event) throws IOException {

		
		if (units.equals("Metric")) {
			double imperialWeight = Double.parseDouble(tfWeight.getText()) * 2.20462;
			tfWeight.setText(Double.toString(imperialWeight));

			double imperialHeight = Double.parseDouble(tfHeight.getText()) * 0.0328084;
			tfHeight.setText(Double.toString(imperialHeight));

			double imperialWaist = Double.parseDouble(tfWaist.getText()) * 0.393701;
			tfWaist.setText(Double.toString(imperialWaist));
		}

		units = "Imperial";
		updateButtonUnderline();
	}
}