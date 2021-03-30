package controllers;

import domain.Module;
import domain.Webcast;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import utils.ResponseHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


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
    private Hyperlink url;
    @FXML
    private Button contentViewedButton;

    private Webcast webcast;

    public WebcastPaneController(Webcast webcast) {
        this.webcast = webcast;
    }

    @FXML
    public void initialize()  {
        this.contentTitle.setText(webcast.getTitle());
        this.publicationDate.setText(webcast.getPublication().toString());
        this.nameSpeaker.setText(webcast.getFirstNameSpeaker() + " " + webcast.getLastNameSpeaker());
        this.nameOrganisation.setText(webcast.getOrganisation());

        url.setText(webcast.getUrl());

        url.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new URI(webcast.getUrl()));
            } catch (Exception err) {
                ResponseHandler.handleError(Alert.AlertType.ERROR,
                        "Couldn't open the link",
                        "We're sorry. Please try it again later.");
            }
        });
    }

}
