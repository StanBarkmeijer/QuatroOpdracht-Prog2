import datastorage.CursistDAO;
import datastorage.DatabaseConnect;
import domain.Cursist;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;

public class KodeAkademy extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("ui/LoginView.fxml");
        Parent root = FXMLLoader.load(url);

        stage.setTitle("KodeAkademy | Made by Stan Barkmeijer (2153846) & Kalle van de Visser (2177927)");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }

    public static void main(String[] args) {

        DatabaseConnect connection = new DatabaseConnect();
        if (connection.getConnection() != null) System.out.println("Connection to the database successful");

        launch(args);

    }

}
