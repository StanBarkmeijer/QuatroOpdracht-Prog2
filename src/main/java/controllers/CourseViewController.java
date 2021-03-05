package controllers;

import domain.Content;
import domain.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

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
