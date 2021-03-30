package controllers;

import datastorage.CourseDAO;
import datastorage.FollowedCursusDAO;
import domain.Course;
import domain.FollowedCursus;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.ResponseHandler;

import java.util.prefs.Preferences;

public class FollowedCoursePaneController {

    @FXML
    private Label courseTitle;
    @FXML
    private Label courseSubject;
    @FXML
    private Label courseIntroduction;
    @FXML
    private Label courseNiveau;
    @FXML
    private Button unfollowCourseButton;

    private Course course;

    public FollowedCoursePaneController(FollowedCursus followedCursus) {
        this.course = new CourseDAO().get(followedCursus.getCursusId());
    }

    @FXML
    public void initialize() {
        this.courseTitle.setText(course.getCourseTitle());
        this.courseSubject.setText(course.getCourseSubject());
        this.courseIntroduction.setText(course.getCourseIntroduction());
        this.courseNiveau.setText(course.getCourseNiveau());

        unfollowCourseButton.setOnAction(x -> {
            int userID = Preferences.userRoot().getInt("user", 0);
            int courseID = this.course.getCourseId();

            FollowedCursusDAO dao = new FollowedCursusDAO();
            FollowedCursus followedCursus = dao.followedFoundWithCursistIDAndCursusID(userID, courseID);

            if (dao.delete(followedCursus)) {
                ResponseHandler
                        .handleError(Alert.AlertType.CONFIRMATION,
                                "Unfollowed course",
                                "Unfollowed the course: " + course.getCourseTitle() + " successfully");
            } else {
                ResponseHandler
                        .handleError(Alert.AlertType.ERROR,
                                "Couldn't unfollow course",
                                "Something went wrong, try again later.");
            }
        });
    }

}
