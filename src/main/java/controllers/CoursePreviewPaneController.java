package controllers;

import domain.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;

public class CoursePreviewPaneController {

    @FXML
    private Label courseTitleId;
    @FXML
    private Label courseNiveauId;
    @FXML
    private Label courseDescriptionId;
    @FXML
    private Button visitCourseId;

    private ScrollPane scrollPane;
    private Course course;

    public CoursePreviewPaneController(ScrollPane scrollPane, Course course) {
        this.scrollPane = scrollPane;
        this.course = course;
    }

    @FXML
    public void initialize() {
        this.courseTitleId.setText(course.getCourseTitle());
        this.courseNiveauId.setText(course.getCourseNiveau());
        this.courseDescriptionId.setText(course.getCourseIntroduction());
    }

    @FXML
    public void visitCourse(MouseEvent mouseEvent) throws IOException {
        URL url = getClass().getResource("../ui/CourseView.fxml");

        FXMLLoader loader = new FXMLLoader(url);

        CourseViewController controller = new CourseViewController(course);

        loader.setController(controller);
        Node node = loader.load();

        scrollPane.setContent(node);
    }

}
