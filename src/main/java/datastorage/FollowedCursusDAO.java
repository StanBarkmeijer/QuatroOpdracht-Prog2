package datastorage;

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


    public List<FollowedCursus> getTop3Webcasts() {
        List<FollowedCursus> contentList = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            final String webcastQuery = "SELECT *, COUNT(ContentID) AS 'ViewedCount'\n" +
                    "FROM FollowedCursus\n" +
                    "WHERE ContentID > 9999\n" +
                    "ORDER BY COUNT(ContentID)";

            PreparedStatement webcastStmt = connection.prepareStatement(webcastQuery);

            ResultSet webcastRs = webcastStmt.executeQuery();

            int i = 0;

            while (webcastRs.next() && i < 3) {
                contentList.add(new FollowedCursus(webcastRs));

                i++;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get all courses", error.getMessage());
        }

        return contentList;
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

    public ArrayList<FollowedCursus> getFromUserID(int id) {
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

    public FollowedCursus followedFoundWithCursistIDAndCursusID(int cursistId, int cursusId) {
        FollowedCursus followedCursus = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM FollowedCursus " +
                    "WHERE CursistId=? " +
                    "AND CursusID=?");

            query.setInt(1, cursistId);
            query.setInt(2, cursusId);

            ResultSet rs = query.executeQuery();

            rs.next();

            followedCursus = new FollowedCursus(rs);
        } catch (SQLException error) {
            return null;
        }

        return followedCursus;
    }

    public FollowedCursus followedFoundWithCursistIDCursusIDandContentID(int cursistId, int cursusId, int contentId) {
        FollowedCursus followedCursus = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM FollowedCursus " +
                    "WHERE CursistId=? " +
                    "AND CursusID=? " +
                    "AND ContentID=?");

            query.setInt(1, cursistId);
            query.setInt(2, cursusId);
            query.setInt(3, contentId);

            ResultSet rs = query.executeQuery();

            rs.next();

            followedCursus = new FollowedCursus(rs);
        } catch (SQLException error) {
            return null;
        }

        return followedCursus;
    }

    @Override
    public boolean save(FollowedCursus followedCursus) {
        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("INSERT INTO FollowedCursus " +
                    "(CursistID, CursusID, Identifier, RegistrationDate, ContentID)" +
                    "VALUES (?, ?, ?, ?, ?)");

            query.setInt(1, followedCursus.getCursistId());
            query.setInt(2, followedCursus.getCursusId());
            query.setString(3, followedCursus.getIdentifier());
            query.setDate(4, new java.sql.Date(followedCursus.getRegistrationDate().getTime()));
            query.setInt(5, followedCursus.getContentId());

            query.execute();

            return true;
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't insert followed cursus", error.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(int id, FollowedCursus followedCursus) {
        return false;
    }

    @Override
    public boolean delete(FollowedCursus followedCursus) {
        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("DELETE FROM FollowedCursus " +
                    "WHERE CursistID=? " +
                    "AND CursusID=?");

            query.setInt(1, followedCursus.getCursistId());
            query.setInt(2, followedCursus.getCursusId());

            query.execute();

            return true;
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't unfollow the course", error.getMessage());
            return false;
        }
    }
}
