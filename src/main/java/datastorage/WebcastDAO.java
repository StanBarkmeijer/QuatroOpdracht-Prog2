package datastorage;

import domain.Webcast;
import javafx.scene.control.Alert;
import utils.ResponseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WebcastDAO implements DAO<Webcast> {

    private DatabaseConnect databaseConnect;

    public WebcastDAO() {
        this.databaseConnect = new DatabaseConnect();
    }

    @Override
    public List<Webcast> getAll() {
        return null;
    }

    @Override
    public Webcast get(int id) {
        Webcast webcast = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Webcast WHERE ContentID=?");

            query.setInt(1, id);

            ResultSet rs = query.executeQuery();

            rs.next();

            webcast = new Webcast(rs);
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get user with ID: " + id, error.getMessage());
        }

        return webcast;
    }

    @Override
    public boolean save(Webcast webcast) {
        return false;
    }

    @Override
    public boolean update(int id, Webcast webcast) {
        return false;
    }

    @Override
    public boolean delete(Webcast webcast) {
        return false;
    }
}
