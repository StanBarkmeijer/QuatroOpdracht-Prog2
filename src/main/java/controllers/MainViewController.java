package controllers;

import datastorage.CursistDAO;
import domain.Cursist;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import utils.ResponseHandler;

import javax.lang.model.type.ErrorType;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MainViewController {

    @FXML
    private Label email;

    @FXML
    public void initialize() {
        try {
            Preferences pref = Preferences.userRoot();
            int id = pref.getInt("user", 0);
            Cursist user = CursistDAO.getCursistFromID(id);

            email.setText(user.getEmail());
        } catch (SQLException throwables) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Something went wrong", throwables.getMessage());
        }
    }
}
