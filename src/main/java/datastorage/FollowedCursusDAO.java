package datastorage;

import domain.Cursist;
import domain.FollowedCursus;
import javafx.scene.control.Alert;
import utils.ResponseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowedCursusDAO implements DAO<FollowedCursus> {

    private DatabaseConnect databaseConnect;

    public FollowedCursusDAO() {
        this.databaseConnect = new DatabaseConnect();
    }

    @Override
    public List<FollowedCursus> getAll() {
        ArrayList<FollowedCursus> list = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            final String query = "SELECT * FROM FollowedCursus";

            ResultSet rs = connection.prepareStatement(query).executeQuery(query);

            while (rs.next()) {
                list.add(new FollowedCursus(rs));
            }
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get all followed courses", error.getMessage());
        }

        return list;
    }

    @Override
    public FollowedCursus get(int id) {
        FollowedCursus followedCursus = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM FollowedCursus WHERE FollowedCursusId=?");

            query.setInt(1, id);

            ResultSet rs = query.executeQuery();

            rs.next();

            followedCursus = new FollowedCursus(rs);
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get user with ID: " + id, error.getMessage());
        }

        return followedCursus;
    }

    public FollowedCursus getFromUserID(int id) {
        FollowedCursus followedCursus = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM FollowedCursus WHERE CursistId=?");

            query.setInt(1, id);

            ResultSet rs = query.executeQuery();

            rs.next();

            followedCursus = new FollowedCursus(rs);
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get user with ID: " + id, error.getMessage());
        }

        return followedCursus;
    }

    @Override
    public boolean save(FollowedCursus followedCursus) {
        return false;
    }

    @Override
    public boolean update(int id, FollowedCursus followedCursus) {
        return false;
    }

    @Override
    public boolean delete(FollowedCursus followedCursus) {
        return false;
    }
}
