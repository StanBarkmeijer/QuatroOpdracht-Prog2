package controllers;

import domain.Module;
import domain.Webcast;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class WebcastPaneController {

    @FXML
    private Label contentTitle;
    @FXML
    private Label publicationDate;
    @FXML
    private Label nameSpeaker;
    @FXML
    private Label nameOrganisation;
    @FXML
    private Label url;

    private Webcast webcast;

    public WebcastPaneController(Webcast webcast) {
        this.webcast = webcast;
    }

    @FXML
    public void initialize() {
        this.contentTitle.setText(webcast.getTitle());
        this.publicationDate.setText(webcast.getPublication().toString());
        this.nameSpeaker.setText(webcast.getFirstNameSpeaker() + " " + webcast.getLastNameSpeaker());
        this.nameOrganisation.setText(webcast.getOrganisation());
        this.url.setText(webcast.getUrl());
    }

}
