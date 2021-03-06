package controllers;

import datastorage.CourseDAO;
import datastorage.CursistDAO;
import datastorage.FollowedCursusDAO;
import datastorage.WebcastDAO;
import domain.Course;
import domain.Cursist;
import domain.FollowedCursus;
import domain.Webcast;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ResponseHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

public class MainViewController {

    private CursistDAO cursistDAO;
    private CourseDAO courseDAO;
    private FollowedCursusDAO followedCoursesDAO;

    @FXML
    private Label firstName;
    @FXML
    private Button signOut;
    @FXML
    private Label coursesButton;
    @FXML
    private Label followedCoursesButton;
    @FXML
    private VBox contentBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label myAccountButton;

    public MainViewController() {
        this.cursistDAO = new CursistDAO();
        this.courseDAO = new CourseDAO();
        this.followedCoursesDAO = new FollowedCursusDAO();
    }

    @FXML
    public void initialize() {
        int id = Preferences.userRoot().getInt("user", 0);

        if (id == 0) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Not logged in",
                    "You are not logged in, please try again after signing out and logging in again");
        }

        Cursist user = cursistDAO.get(id);

        firstName.setText("Logged in as: " + user.getFirstName());
    }

    @FXML
    public void signOut() throws IOException {
        Stage stage = (Stage) signOut.getScene().getWindow();
        URL url = getClass().getResource("../ui/LoginView.fxml");
        Parent root = FXMLLoader.load(url);

        Preferences preferences = Preferences.userRoot();
        preferences.remove("user");

        stage.setScene(new Scene(root, 600, 600));
    }

    public void handleCourseButton(MouseEvent mouseEvent) throws IOException {
        List<Course> courses = this.courseDAO.getAll();

        HBox searchPart = new HBox();

        TextField searchBar = new TextField();
        searchBar.setId("searchBar");
        searchBar.setPrefWidth(300);

        Button button = new Button();
        button.setId("searchButton");
        button.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #E76F51, #F69982);");
        button.setText("Search");
        button.setTextFill(Color.WHITE);

        Button findSuggestedCourse = new Button();
        findSuggestedCourse.setId("suggestedButton");
        findSuggestedCourse.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #E76F51, #F69982);");
        findSuggestedCourse.setText("Suggested course");
        findSuggestedCourse.setTextFill(Color.WHITE);

        searchPart.getChildren().addAll(searchBar, button, findSuggestedCourse);

        button.setOnAction(x -> {
            VBox contentBox = new VBox();
            HBox box = new HBox();

            box.getChildren().addAll(searchBar, searchPart);
            contentBox.getChildren().add(box);

            String input = searchBar.getText();

            List<Course> foundCourses = this.courseDAO.findByName(input);

            URL url = getClass().getResource("../ui/CoursePreviewPane.fxml");

            for (Course course : foundCourses) {
                FXMLLoader loader = new FXMLLoader(url);

                CoursePreviewPaneController controller = new CoursePreviewPaneController(
                        scrollPane, course
                );

                loader.setController(controller);
                Node node = null;

                try {
                    node = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                contentBox.getChildren().add(node);
            }

            scrollPane.setContent(contentBox);
        });

        findSuggestedCourse.setOnAction(x -> {
            List<Course> courseList = courseDAO.getAll();

            Random rand = new Random();
            Course randomCourse = courseList.get(rand.nextInt(courseList.size()));

            VBox vBox = new VBox();

            URL url = getClass().getResource("../ui/CoursePreviewPane.fxml");
            FXMLLoader loader = new FXMLLoader(url);

            CoursePreviewPaneController controller = new CoursePreviewPaneController(scrollPane, randomCourse);

            loader.setController(controller);
            Node node = null;

            try {
                node = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            vBox.getChildren().add(node);

            scrollPane.setContent(vBox);
        });

        VBox contentBox = new VBox();

        contentBox.getChildren().add(searchPart);

        URL url = getClass().getResource("../ui/CoursePreviewPane.fxml");

        for (Course course : courses) {
            FXMLLoader loader = new FXMLLoader(url);

            CoursePreviewPaneController controller = new CoursePreviewPaneController(
                    scrollPane, course
            );

            loader.setController(controller);
            Node node = loader.load();

            contentBox.getChildren().add(node);
        }

        scrollPane.setContent(contentBox);
    }

    public void handleMyAccountButton(MouseEvent mouseEvent) throws IOException {
        URL url = getClass().getResource("../ui/EditCursist.fxml");
        Node node = FXMLLoader.load(url);

        scrollPane.setContent(node);
    }

    public void handleFollowedCoursesButton(MouseEvent mouseEvent) throws IOException {
        URL url = getClass().getResource("../ui/FollowedCoursesView.fxml");
        FXMLLoader loader = new FXMLLoader(url);

        FollowedCoursesViewController controller = new FollowedCoursesViewController(scrollPane);

        loader.setController(controller);
        Node node = loader.load();

        scrollPane.setContent(node);
    }


    public void mostViewedWebcasts(MouseEvent mouseEvent) throws IOException {
        List<FollowedCursus> topThree = followedCoursesDAO.getTop3Webcasts();
        List<Webcast> topThreeWebcasts = new ArrayList<>();

        for (FollowedCursus followedCursus : topThree) {
            int contentID = followedCursus.getContentId();

            Webcast foundWebcast = new WebcastDAO().get(contentID);

            topThreeWebcasts.add(foundWebcast);
        }

        VBox vBox = new VBox();

        for (Webcast webcast : topThreeWebcasts) {
            URL url = getClass().getResource("../ui/WebcastPane.fxml");

            FXMLLoader loader = new FXMLLoader(url);

            WebcastPaneController controller = new WebcastPaneController(webcast);

            loader.setController(controller);
            Node node = loader.load();

            vBox.getChildren().add(node);
        }

        scrollPane.setContent(vBox);
    }
}
