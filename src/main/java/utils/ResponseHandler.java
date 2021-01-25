package utils;

import javafx.scene.control.Alert;

public class ResponseHandler {

    public static void handleError(Alert.AlertType alertType, String header, String message) {
        Alert alert = new Alert(alertType);

        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
