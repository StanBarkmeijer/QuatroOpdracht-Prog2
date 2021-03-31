package controllers;

import datastorage.CourseDAO;
import datastorage.CursistDAO;
import domain.Course;
import domain.Cursist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import utils.ResponseHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.prefs.Preferences;

public class MainViewController {

    private CursistDAO cursistDAO;
    private CourseDAO courseDAO;

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

        searchPart.getChildren().addAll(searchBar, button);

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
        Node node = FXMLLoader.load(url);

        scrollPane.setContent(node);
    }
}
