package controllers;

import datastorage.CourseDAO;
import datastorage.CursistDAO;
import domain.Course;
import domain.Cursist;
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
    private int courseId;
    private String courseTitle;
    private String courseNiveau;
    private String courseDescription;

    public CoursePreviewPaneController(ScrollPane scrollPane, int id, String courseTitle, String courseNiveau, String courseDescription) {
        this.scrollPane = scrollPane;
        this.courseId = id;
        this.courseTitle = courseTitle;
        this.courseNiveau = courseNiveau;
        this.courseDescription = courseDescription;
    }

    @FXML
    public void initialize() {
        this.courseTitleId.setText(courseTitle);
        this.courseNiveauId.setText(courseNiveau);
        this.courseDescriptionId.setText(courseDescription);
    }

    @FXML
    public void visitCourse(MouseEvent mouseEvent) throws IOException {
        URL url = getClass().getResource("../ui/CourseView.fxml");

        Course course = new CourseDAO().get(courseId);

        FXMLLoader loader = new FXMLLoader(url);

        CourseViewController controller = new CourseViewController(
                course.getCourseId(),
                course.getCourseTitle(),
                course.getCourseNiveau(),
                course.getCourseSubject(),
                course.getCourseIntroduction()
        );

        loader.setController(controller);
        Node node = loader.load();

        scrollPane.setContent(node);
    }

}
