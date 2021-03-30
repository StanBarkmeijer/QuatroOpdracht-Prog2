package controllers;

import datastorage.FollowedCursusDAO;
import domain.FollowedCursus;
import domain.Module;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.ResponseHandler;

import java.util.Date;
import java.util.prefs.Preferences;


public class ModulePaneController {

    @FXML
    private Label contentTitle;
    @FXML
    private Label publicationDate;
    @FXML
    private Label nameContact;
    @FXML
    private Label description;
    @FXML
    private Label version;
    @FXML
    private Button contentViewedButton;

    private Module module;

    public ModulePaneController(Module module) {
        this.module = module;
    }

    @FXML
    public void initialize() {
        this.contentTitle.setText(module.getTitle());
        this.publicationDate.setText(module.getPublication().toString());
        this.nameContact.setText(module.getFirstNameContact() + " " + module.getLastNameContact());
        this.description.setText(module.getDescription());
        this.version.setText(module.getVersion());
    }

}
