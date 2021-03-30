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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.ResponseHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.prefs.Preferences;

public class CourseViewController {

    @FXML
    private Label courseTitle;
    @FXML
    private Label courseNiveau;
    @FXML
    private Label courseSubject;
    @FXML
    private Label courseIntroduction;
    @FXML
    private VBox vBox;
    @FXML
    private Button courseFollowedButton;

    private Course course;

    public CourseViewController(Course course) {
        this.course = course;
    }

    @FXML
    public void initialize() throws IOException {
        this.courseTitle.setText(course.getCourseTitle());
        this.courseNiveau.setText(course.getCourseNiveau());
        this.courseSubject.setText(course.getCourseSubject());
        this.courseIntroduction.setText(course.getCourseIntroduction());

        courseFollowedButton.setOnAction(x -> {
            FollowedCursusDAO followedCursusDAO = new FollowedCursusDAO();

            int cursistId = Preferences.userRoot().getInt("user", 0);

            if (followedCursusDAO.followedFoundWithCursistIDAndCursusID(cursistId, course.getCourseId())) {
                ResponseHandler.handleError(Alert.AlertType.WARNING,
                        "Content already followed",
                        "You already followed the lesson: " + course.getCourseTitle() + ", woops!");

                return;
            }

            System.out.println(cursistId);

            FollowedCursus toInsert = new FollowedCursus(cursistId, course.getCourseId(), "identifier", new Date());

            boolean res = followedCursusDAO.save(toInsert);

            if (res) {
                ResponseHandler.handleError(Alert.AlertType.CONFIRMATION,
                        "Content followed",
                        "You followed the course: " + course.getCourseTitle() + ", great job!");
            }
        });

        for (Content content : course.getContentList()) {
            if (content.getType().equals("Module")) {
                URL url = getClass().getResource("../ui/ModulePane.fxml");

                FXMLLoader loader = new FXMLLoader(url);

                ModulePaneController controller = new ModulePaneController((domain.Module) content);

                loader.setController(controller);
                Node node = loader.load();

                vBox.getChildren().add(node);
            } else {
                URL url = getClass().getResource("../ui/WebcastPane.fxml");

                FXMLLoader loader = new FXMLLoader(url);

                WebcastPaneController controller = new WebcastPaneController((domain.Webcast) content);

                loader.setController(controller);
                Node node = loader.load();

                vBox.getChildren().add(node);
            }
        }

    }
}
