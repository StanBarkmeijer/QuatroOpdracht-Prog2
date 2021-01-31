package datastorage;

import domain.Cursist;
import javafx.scene.control.Alert;
import utils.ResponseHandler;

import javax.lang.model.type.ErrorType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class CursistDAO implements DAO<Cursist>{

    private DatabaseConnect databaseConnect;

    public CursistDAO() {
        this.databaseConnect = new DatabaseConnect();
    }

    public Cursist login(String email, String password) {
        Cursist cursist = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Cursist WHERE EMail=? AND Password=?");

            query.setString(1, email);
            query.setString(2, password);

            ResultSet rs = query.executeQuery();

            rs.next();

            cursist = new Cursist(rs);

            connection.close();
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't login", error.getMessage());
        }

        return cursist;
    }

    @Override
    public List<Cursist> getAll() {
        ArrayList<Cursist> list = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            final String query = "SELECT * FROM Cursist\n" +
                    "INNER JOIN Address\n" +
                    "ON Cursist.addressId = address.addressId";

            ResultSet rs = connection.prepareStatement(query).executeQuery(query);

            while (rs.next()) {
                list.add(new Cursist(rs));
            }
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get all users", error.getMessage());
        }

        return list;
    }

    @Override
    public Cursist get(int id) {
        Cursist cursist = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Cursist WHERE CursistID=?");

            query.setInt(1, id);

            ResultSet rs = query.executeQuery();

            rs.next();

            cursist = new Cursist(rs);
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get user with ID: " + id, error.getMessage());
        }

        return cursist;
    }

    @Override
    public void save(Cursist cursist) {}

    @Override
    public void update(Cursist cursist, String[] params) {

    }

    @Override
    public void delete(Cursist cursist) {

    }
}
