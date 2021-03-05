package controllers;

import domain.Module;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


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
