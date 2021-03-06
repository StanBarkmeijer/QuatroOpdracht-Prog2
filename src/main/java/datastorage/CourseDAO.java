package datastorage;

import domain.Course;
import javafx.scene.control.Alert;
import utils.ResponseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements DAO<Course> {

    private DatabaseConnect databaseConnect;

    public CourseDAO() {
        this.databaseConnect = new DatabaseConnect();
    }

    @Override
    public List<Course> getAll() {
        ArrayList<Course> list = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            final String query = "SELECT * FROM Cursus";

            ResultSet rs = connection.prepareStatement(query).executeQuery(query);

            while (rs.next()) {
                list.add(new Course(rs));
            }
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get all courses", error.getMessage());
        }

        return list;
    }

    public List<Course> findByName(String name) {
        ArrayList<Course> list = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            PreparedStatement query = connection.prepareStatement("SELECT * FROM Cursus " +
                    "WHERE CursusNaam LIKE ?");

            query.setString(1, "%" + name + "%");

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                list.add(new Course(rs));
            }
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR,
                    "Couldn't find courses with name: " + name,
                    error.getMessage());
        }

        return list;
    }

    @Override
    public Course get(int id) {
        Course course = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Cursus WHERE CursusId=?");

            query.setInt(1, id);

            ResultSet rs = query.executeQuery();

            rs.next();

            course = new Course(rs);
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get course with ID: " + id, error.getMessage());
        }

        return course;
    }

    @Override
    public boolean save(Course course) {
        return false;
    }

    @Override
    public boolean update(int id, Course course) {
        return false;
    }

    @Override
    public boolean delete(Course course) {
        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("DELETE FROM Cursist " +
                    "WHERE CursistID=?");

            query.setInt(1, course.getCourseId());

            query.execute();

            return true;
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't insert user", error.getMessage());
            return false;
        }
    }
}
