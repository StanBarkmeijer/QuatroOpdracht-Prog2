package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Label signUp;

    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();

        System.out.println(emailField.getText());
        System.out.println(passwordField.getText());
    }

    @FXML
    public void handleSignUpClick(MouseEvent event) throws IOException {
        Stage stage =  (Stage) signUp.getScene().getWindow();
        URL url = getClass().getResource("../ui/SignUpView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setScene(new Scene(root, 750, 700));
    }

}
