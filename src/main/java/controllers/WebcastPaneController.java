package controllers;

import datastorage.FollowedCursusDAO;
import domain.FollowedCursus;
import domain.Webcast;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import utils.ResponseHandler;

import java.net.URI;
import java.util.Date;
import java.util.prefs.Preferences;


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

        contentViewedButton.setOnAction(x -> {
            FollowedCursusDAO followedCursusDAO = new FollowedCursusDAO();

            int cursistId = Preferences.userRoot().getInt("user", 0);

            if (followedCursusDAO.followedFoundWithCursistIDCursusIDandContentID(cursistId, webcast.getCursusId(), webcast.getContentId()) != null) {
                ResponseHandler.handleError(Alert.AlertType.WARNING,
                        "Content already followed",
                        "You already followed the webcast: " + webcast.getTitle() + ", woops!");

                return;
            }

            FollowedCursus toInsert = new FollowedCursus(cursistId, webcast.getCursusId(), "identifier", new Date(), webcast.getContentId());

            boolean res = followedCursusDAO.save(toInsert);

            if (res) {
                ResponseHandler.handleError(Alert.AlertType.CONFIRMATION,
                        "Content followed",
                        "You followed the webcast: " + webcast.getTitle() + ", great job!");
            }
        });
    }

}
