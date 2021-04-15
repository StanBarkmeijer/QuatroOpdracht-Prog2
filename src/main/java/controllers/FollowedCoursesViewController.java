package controllers;

import datastorage.FollowedCursusDAO;
import domain.Content;
import domain.Course;
import domain.FollowedCursus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.ResponseHandler;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;

public class FollowedCoursesViewController {

    @FXML
    private VBox vBox;

    @FXML
    public void initialize() throws IOException {
        FollowedCursusDAO followedCursusDAO = new FollowedCursusDAO();

        List<FollowedCursus> followedContent = followedCursusDAO
                .getFromUserID(Preferences.userRoot().getInt("user", 0));

        Map<Integer, FollowedCursus> added = new HashMap<>();

        for (FollowedCursus cursus : followedContent) {
            if (added.containsKey(cursus.getCursusId())) continue;
            else {
                added.put(cursus.getCursusId(), cursus);
            }

            URL url = getClass().getResource("../ui/FollowedCoursePane.fxml");
            FXMLLoader loader = new FXMLLoader(url);

            FollowedCoursePaneController controller = new FollowedCoursePaneController(cursus);

            loader.setController(controller);
            Node node = loader.load();

            vBox.getChildren().add(node);
        }
    }


}
