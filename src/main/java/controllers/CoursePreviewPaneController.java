package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CoursePreviewPaneController {

    @FXML
    private Label courseTitleId;
    @FXML
    private Label courseNiveauId;
    @FXML
    private Label courseDescriptionId;
    @FXML
    private Button visitCourseId;

    private int courseId;
    private String courseTitle;
    private String courseNiveau;
    private String courseDescription;

    public CoursePreviewPaneController(int id, String courseTitle, String courseNiveau, String courseDescription) {
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
    public void visitCourse(MouseEvent mouseEvent) {
        System.out.println("Clicked course with ID: " + this.courseId);
    }

}
