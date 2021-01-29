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

    private CursistDAO cursistDAO;

    @FXML
    private Label firstName;
    @FXML
    private Button signOut;

    public MainViewController() {
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

        firstName.setText("Logged in as: " + user.getFirstName());
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
