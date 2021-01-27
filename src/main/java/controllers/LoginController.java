package controllers;

import datastorage.CursistDAO;
import domain.Address;
import domain.Cursist;
import javafx.scene.control.*;
import utils.ResponseHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Label signUp;

    @FXML
    public void handleSubmitButtonAction(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Login error", "One or more fields are empty");
            return;
        }

        try {
            Cursist user = CursistDAO.getCursistFromEmailAndPassword(email, password);

            if (user == null) {
                ResponseHandler.handleError(Alert.AlertType.ERROR, "Login error", "Account was not found.");
                return;
            }

            Preferences preferences = Preferences.userRoot();
            preferences.put("user", user.getId() + "");

            ResponseHandler.handleError(Alert.AlertType.INFORMATION,
                    "Login message",
                    "Successfully logged in to " + CursistDAO.getLoggedInCursist().getEmail() +
                            "\nClick the button to continue to the next window");

            Stage stage =  (Stage) signUp.getScene().getWindow();
            URL url = getClass().getResource("../ui/MainView.fxml");
            Parent root = FXMLLoader.load(url);

            stage.setScene(new Scene(root));
        } catch (SQLException e) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Something went wrong with the SQL statement", e.getMessage());
        }
    }

    @FXML
    public void handleSignUpClick(MouseEvent event) throws IOException {
        Stage stage =  (Stage) signUp.getScene().getWindow();
        URL url = getClass().getResource("../ui/SignUpView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setScene(new Scene(root, 750, 700));
    }

}
