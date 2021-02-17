package controllers;

import datastorage.CursistDAO;
import domain.Cursist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    @FXML
    private Label firstName;
    @FXML
    private Button signOut;
    @FXML
    private Label coursesButton;
    @FXML
    private VBox contentBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label myAccountButton;

    public MainViewController() {
        this.cursistDAO = new CursistDAO();
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

    public void handleCourseButton(MouseEvent mouseEvent) {
        List<Cursist> cursists = this.cursistDAO.getAll();

        VBox contentBox = new VBox();

        for (Cursist cursist : cursists) {
            Pane pane = new Pane();
            pane.setId("cursistPane" + cursist.getCursistId());
            pane.setPrefHeight(116.0);
            pane.setPrefWidth(436.0);

            Label name = new Label(cursist.getFirstName());
            name.setId("courseName" + cursist.getCursistId());
            name.setPrefHeight(34.0);
            name.setPrefWidth(529.0);
            name.setPadding(new Insets(0, 0, 0, 10));
            name.setStyle("-fx-font-size: 14");

            Label content = new Label(cursist.getEmail());
            content.setId("courseOnderwerp" + cursist.getCursistId());
            content.setPrefHeight(75.0);
            content.setPrefWidth(538.0);
            content.setPadding(new Insets(0, 0, 0, 10));
            content.setLayoutY(42.0);
            content.setAlignment(Pos.TOP_LEFT);

            Label niveau = new Label(cursist.getPostalCode());
            niveau.setId("courseName" + cursist.getCursistId());
            niveau.setPrefHeight(34.0);
            niveau.setPrefWidth(529.0);
            niveau.setPadding(new Insets(0, 0, 0, 10));
            niveau.setLayoutX(532.0);

            pane.getChildren().addAll(name, content, niveau);
            pane.setStyle("-fx-border-color: #7c8184; -fx-border-width: 0 0 1 0");

            contentBox.getChildren().add(pane);
        }

        scrollPane.setContent(contentBox);
    }

    public void handleMyAccountButton(MouseEvent mouseEvent) throws IOException {
        URL url = getClass().getResource("../ui/EditCursist.fxml");
        Node node = FXMLLoader.load(url);

        scrollPane.setContent(node);
    }
}
