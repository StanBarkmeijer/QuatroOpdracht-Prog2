package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

public class SignUpController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button cancelButton;

    @FXML
    public void handleSignUpButton(ActionEvent event) {
        Window owner = signUpButton.getScene().getWindow();

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String country = countryField.getText();
        String postalCode = postalCodeField.getText();
        String password = passwordField.getText();
    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Stage stage =  (Stage) cancelButton.getScene().getWindow();
        URL url = getClass().getResource("../ui/LoginView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setScene(new Scene(root, 600, 600));
    }

}
