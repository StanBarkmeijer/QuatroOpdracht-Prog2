package controllers;

import datastorage.DatabaseConnect;
import javafx.scene.control.*;
import utils.ResponseHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Label signUp;

//    private User user;

//    public static User getUser () {
//        return this.user;
//    }

    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();

        String email = emailField.getText();
        String password = passwordField.getText();

        String query = String.format("SELECT * FROM User WHERE EMail='%s' AND Password='%s'", email, password);

        try {
            Map<String, String> map = new HashMap<>();

            DatabaseConnect databaseConnect = new DatabaseConnect();
            ResultSet rs = databaseConnect.getConnection().prepareStatement(query).executeQuery();

            while (rs.next()) {
                map.put(rs.getString("EMail"), rs.getString("Password"));
            }

            if (map.size() == 1) {
//                this.user = new User(rs);
                ResponseHandler.handleError(Alert.AlertType.INFORMATION, "Login message", "Successfully logged in");
            } else {
                ResponseHandler.handleError(Alert.AlertType.WARNING, "Login warning", "Your credentials are wrong");
            }
        } catch (SQLException e) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Something went wrong with the SQL statement", e.getMessage());
        }
    }

    @FXML
    public void handleSignUpClick(MouseEvent event) throws IOException {
        Stage stage =  (Stage) signUp.getScene().getWindow();
        URL url = getClass().getResource("../ui/SignUpView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setScene(new Scene(root, 750, 700));
    }

}
