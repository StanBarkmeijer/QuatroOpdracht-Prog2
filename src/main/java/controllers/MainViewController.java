package controllers;

import datastorage.CursistDAO;
import domain.Cursist;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import utils.ResponseHandler;

import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MainViewController {

    @FXML
    private Label email;

    @FXML
    public void initialize() {
        try {
            Cursist user = CursistDAO.getLoggedInCursist();

            email.setText("Logged in as: " + user.getEmail());
        } catch (SQLException throwables) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Something went wrong", throwables.getMessage());
        }
    }
}
