package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CreateAccountController extends BaseLoginController implements Initializable {

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	protected void handleBtnBack(ActionEvent event) throws IOException {
		System.out.println("Back btn pressed");
		
		
		loadFXML(event, "/view/login.fxml");
	}
	
	@FXML
	protected void handleBtnSubmitAccount(ActionEvent event) throws IOException {
		System.out.println("Try to create an account now!");
	}
}