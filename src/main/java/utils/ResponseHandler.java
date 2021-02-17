package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ResponseHandler {

    public static Optional<ButtonType> handleError(Alert.AlertType alertType, String header, String message) {
        Alert alert = new Alert(alertType);

        alert.setHeaderText(header);
        alert.setContentText(message);
        return alert.showAndWait();
    }

}
