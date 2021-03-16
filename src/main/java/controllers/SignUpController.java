package controllers;

import datastorage.CursistDAO;
import domain.Cursist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.ResponseHandler;
import utils.SignUpValidator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SignUpController {

    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField emailField;
    @FXML
    DatePicker birthDayPicker;
    @FXML
    TextField streetField;
    @FXML
    TextField cityField;
    @FXML
    TextField countryField;
    @FXML
    TextField homeNumberField;
    @FXML
    TextField postalCodeField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField retypePasswordField;
    @FXML
    TextField genderField;
    @FXML
    Button signUpButton;
    @FXML
    Button cancelButton;

    @FXML
    public void handleSignUpButton(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        java.sql.Date birthDay = null;

        try {
            birthDay = java.sql.Date.valueOf(birthDayPicker.getValue());
        } catch (Exception e) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Couldn't create account. Error messages below",
                    "Birthday field is empty");

            return;
        }

        String street = streetField.getText();
        String city = cityField.getText();
        String country = countryField.getText();
        String homeNumber = homeNumberField.getText();
        String postalCode = postalCodeField.getText();
        String gender = genderField.getText();
        String password = passwordField.getText();
        String retypedPassword = retypePasswordField.getText();

        List<String> validator = new SignUpValidator(firstName, lastName, email,
                street, city, country, homeNumber, postalCode, gender, password, retypedPassword).validate();

        if (!validator.isEmpty()) {
            String messages = "";

            for (String err : validator) {
                messages += err + "\n";
            }

            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Couldn't create account. Error messages below",
                    messages);

            return;
        }

        if (!password.equals(retypedPassword)) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Couldn't create account",
                    "The password and retyped password don't match");

            return;
        }

        try {
            if (Double.isNaN(Double.parseDouble(homeNumber))) {
                ResponseHandler.handleError(Alert.AlertType.ERROR,
                        "Couldn't create account",
                        "The home number is not an actual number");

                return;
            }
        } catch (Exception e) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Couldn't create account",
                    "The home number is not an actual number");

            return;
        }

        Cursist toInsert = new Cursist(email, firstName, lastName,
                birthDay, gender, password, street, parseInt(homeNumber), postalCode, city, country);

        boolean res = new CursistDAO().save(toInsert);

        if (res) {
            ResponseHandler.handleError(Alert.AlertType.CONFIRMATION, "Success", "Account succesfully registered");

            Stage stage =  (Stage) cancelButton.getScene().getWindow();
            URL url = getClass().getResource("../ui/LoginView.fxml");
            Parent root = FXMLLoader.load(url);

            stage.setScene(new Scene(root, 600, 600));
        }
    }

    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        Stage stage =  (Stage) cancelButton.getScene().getWindow();
        URL url = getClass().getResource("../ui/LoginView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setScene(new Scene(root, 600, 600));
    }

}
