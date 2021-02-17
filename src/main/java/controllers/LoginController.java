package controllers;

import datastorage.CursistDAO;
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
        CursistDAO cursistDAO = new CursistDAO();

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Login error", "One or more fields are empty");
            return;
        }

        Cursist user = cursistDAO.login(email, password);

        if (user == null) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Login error", "Account was not found.");
            return;
        }

        Preferences preferences = Preferences.userRoot();
        preferences.put("user", user.getCursistId() + "");

        Stage stage =  (Stage) signUp.getScene().getWindow();
        URL url = getClass().getResource("../ui/MainView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setScene(new Scene(root));
    }

    @FXML
    public void handleSignUpClick(MouseEvent event) throws IOException {
        Stage stage =  (Stage) signUp.getScene().getWindow();
        URL url = getClass().getResource("../ui/SignUpView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setScene(new Scene(root, 750, 700));
    }

}
