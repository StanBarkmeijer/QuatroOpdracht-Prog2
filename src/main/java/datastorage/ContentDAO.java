package datastorage;

import domain.Content;
import domain.Module;
import domain.Webcast;
import javafx.scene.control.Alert;
import utils.ResponseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO implements DAO<Content> {

    private DatabaseConnect databaseConnect;

    public ContentDAO() {
        this.databaseConnect = new DatabaseConnect();
    }

    @Override
    public List<Content> getAll() {
        return null;
    }

    public List<Content> getAllFromCourseId(int id) {
        List<Content> contentList = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            final String courseQuery = "SELECT * FROM Module WHERE CursusId=?";
            final String webcastQuery = "SELECT * FROM Webcast WHERE CursusId=?";

            PreparedStatement moduleStmt = connection.prepareStatement(courseQuery);

            moduleStmt.setInt(1, id);
            ResultSet moduleRs = moduleStmt.executeQuery();

            PreparedStatement webcastStmt = connection.prepareStatement(webcastQuery);

            webcastStmt.setInt(1, id);
            ResultSet webcastRs = webcastStmt.executeQuery();


            while (moduleRs.next()) {
                contentList.add(new Module(moduleRs));
            }

            while(webcastRs.next()) {
                contentList.add(new Webcast(webcastRs));
            }
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get all courses", error.getMessage());
        }

        return contentList;
    }

    @Override
    public Content get(int id) {
        return null;
    }

    @Override
    public boolean save(Content content) {
        return false;
    }

    @Override
    public boolean update(int id, Content content) {
        return false;
    }

    @Override
    public boolean delete(Content content) {
        return false;
    }
}
