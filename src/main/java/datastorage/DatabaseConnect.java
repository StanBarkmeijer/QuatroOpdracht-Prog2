package datastorage;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private Connection connection;

    public DatabaseConnect() {
        Dotenv dotenv = Dotenv.configure().load();

        final String DB_HOST = dotenv.get("DB_HOST");
        final String DB_USERNAME = dotenv.get("DB_USERNAME");
        final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

        final String connectionURI = String.format("jdbc:mysql://%s:3306/%s?serverTimezone=UTC", DB_HOST, DB_USERNAME);

        try {
            this.connection = DriverManager.getConnection(connectionURI, DB_USERNAME, DB_PASSWORD);

            System.out.println("Connection successful!");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public Connection getConnection() { return this.connection; }

}
