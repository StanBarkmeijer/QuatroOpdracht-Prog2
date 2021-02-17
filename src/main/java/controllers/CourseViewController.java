package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CourseViewController {

    @FXML
    private Label courseTitle;
    @FXML
    private Label courseNiveau;
    @FXML
    private Label courseSubject;
    @FXML
    private Label courseIntroduction;

    private int id;
    private String title;
    private String niveau;
    private String description;
    private String introduction;

    public CourseViewController(int id, String title, String niveau, String description, String introduction) {
        this.id = id;
        this.title = title;
        this.niveau = niveau;
        this.description = description;
        this.introduction = introduction;
    }

    @FXML
    public void initialize() {
        this.courseTitle.setText(title);
        this.courseNiveau.setText(niveau);
        this.courseSubject.setText(description);
        this.courseIntroduction.setText(introduction);
    }
}
