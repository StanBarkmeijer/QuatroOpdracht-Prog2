package controllers;

import datastorage.CursistDAO;
import domain.Cursist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.ResponseHandler;
import utils.SignUpValidator;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.prefs.Preferences;

import static java.lang.Integer.parseInt;

public class EditCursistController {

    private CursistDAO cursistDAO;

    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField emailField;
    @FXML
    DatePicker dateField;
    @FXML
    TextField streetField;
    @FXML
    TextField cityField;
    @FXML
    TextField countryField;
    @FXML
    TextField homeNrField;
    @FXML
    TextField postalCodeField;
    @FXML
    TextField genderField;
    @FXML
    Button saveAccountButton;
    @FXML
    Button deleteAccountButton;

    public EditCursistController() {
        this.cursistDAO = new CursistDAO();
    }

    @FXML
    public void initialize() {
        int id = Preferences.userRoot().getInt("user", 0);

        if (id == 0) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Not logged in",
                    "You are not logged in, please try again after signing out and logging in again");
        }

        Cursist user = cursistDAO.get(id);

        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        emailField.setText(user.getEmail());

        int[] splittedDate = Arrays
                .stream(user.getBirthDay().toString().split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();

        dateField.setValue(LocalDate.of(splittedDate[0], splittedDate[1], splittedDate[2]));

        streetField.setText(user.getStreet());
        cityField.setText(user.getResidency());
        countryField.setText(user.getCountry());
        homeNrField.setText(user.getNumber() + "");
        postalCodeField.setText(user.getPostalCode());
        genderField.setText(user.getGender());
    }


    public void saveAccount(MouseEvent mouseEvent) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        java.sql.Date birthDay = null;

        try {
            birthDay = java.sql.Date.valueOf(dateField.getValue());
        } catch (Exception e) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Couldn't create account. Error messages below",
                    "Birthday field is empty");

            return;
        }

        String street = streetField.getText();
        String city = cityField.getText();
        String country = countryField.getText();
        String homeNumber = homeNrField.getText();
        String postalCode = postalCodeField.getText();
        String gender = genderField.getText();

        String password = cursistDAO.get(Preferences.userRoot().getInt("user", 0)).getPassword();
        String retypedPassword = cursistDAO.get(Preferences.userRoot().getInt("user", 0)).getPassword();

        List<String> validator = new SignUpValidator(firstName, lastName, email,
                street, city, country, homeNumber, postalCode, gender, password, retypedPassword).validate();

        if (!validator.isEmpty()) {
            String messages = "";

            for (String err : validator) {
                messages += err + "\n";
            }

            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Couldn't save account. Error messages below",
                    messages);

            return;
        }

        Cursist toSave = new Cursist(email, firstName, lastName,
                birthDay, gender, password, street, parseInt(homeNumber), postalCode, city, country);

        int id = Preferences.userRoot().getInt("user", 0);

        if (id == 0) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Not logged in",
                    "You are not logged in, please try again after signing out and logging in again");
        }

        boolean res = cursistDAO.update(id, toSave);

        if (res) {
            ResponseHandler.handleError(Alert.AlertType.CONFIRMATION, "Success", "Account succesfully updated");
        }
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException {
        Optional<ButtonType> res = ResponseHandler.handleError(Alert.AlertType.CONFIRMATION,
                "Warning!",
                "Are you sure you want to delete this account?");

        if (res.get() == ButtonType.OK) {
            int id = Preferences.userRoot().getInt("user", 0);

            cursistDAO.delete(cursistDAO.get(id));

            Preferences.userRoot().remove("user");

            Stage stage =  (Stage) deleteAccountButton.getScene().getWindow();
            URL url = getClass().getResource("../ui/LoginView.fxml");
            Parent root = FXMLLoader.load(url);

            stage.setScene(new Scene(root));
        } else {
            ResponseHandler.handleError(Alert.AlertType.INFORMATION,
                    "Canceled",
                    "Canceled the delete request");
        }
    }
}
