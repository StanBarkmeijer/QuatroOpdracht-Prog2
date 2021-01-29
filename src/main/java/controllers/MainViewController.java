package controllers;

import datastorage.CursistDAO;
import domain.Cursist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.ResponseHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MainViewController {

    @FXML
    private Label firstName;
    @FXML
    private Button signOut;

    @FXML
    public void initialize() throws Exception {
        try {
            Cursist user = CursistDAO.getLoggedInCursist();

            firstName.setText("Logged in as: " + user.getFirstName());
        } catch (SQLException throwables) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Something went wrong", throwables.getMessage());
        }
    }

    @FXML
    public void signOut() throws IOException {
        Stage stage =  (Stage) signOut.getScene().getWindow();
        URL url = getClass().getResource("../ui/LoginView.fxml");
        Parent root = FXMLLoader.load(url);

        Preferences preferences = Preferences.userRoot();
        preferences.remove("user");

        stage.setScene(new Scene(root, 600, 600));
    }
}
