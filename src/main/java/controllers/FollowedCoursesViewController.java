package controllers;

import datastorage.CourseDAO;
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
import javafx.scene.control.ScrollPane;
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
    private ScrollPane scrollPane;

    public FollowedCoursesViewController(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

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

            node.setOnMouseClicked(event -> {
                URL course_url = getClass().getResource("../ui/CourseView.fxml");

                FXMLLoader course_loader = new FXMLLoader(course_url);

                CourseDAO courseDAO = new CourseDAO();
                Course searchObject = courseDAO.get(cursus.getCursusId());
                CourseViewController course_controller = new CourseViewController(searchObject);

                course_loader.setController(course_controller);

                try {
                    Node course_node = course_loader.load();

                    scrollPane.setContent(course_node);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            vBox.getChildren().add(node);
        }
    }


}
