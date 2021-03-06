package datastorage;

import domain.Cursist;
import javafx.scene.control.Alert;
import utils.ResponseHandler;
import utils.SignUpValidator;
import utils.UpdateCursistHandler;

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
            return cursist;
        }

        return cursist;
    }

    @Override
    public List<Cursist> getAll() {
        ArrayList<Cursist> list = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            final String query = "SELECT * FROM Cursist";

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

    public Cursist getByEmail(String email) {
        Cursist cursist = null;

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("SELECT * FROM Cursist WHERE EMail=?");

            query.setString(1, email);

            ResultSet rs = query.executeQuery();

            rs.next();

            cursist = new Cursist(rs);
        } catch (SQLException error) {
            return null;
        }

        return cursist;
    }

    @Override
    public boolean save(Cursist cursist) {
        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("INSERT INTO Cursist " +
                    "(Email, FirstName, LastName, BirthDay, Geslacht, Password, Street, Number, PostalCode, Residency, Country)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            List<String> validator = new SignUpValidator(cursist.getFirstName(), cursist.getLastName(), cursist.getEmail(),
                    cursist.getStreet(), cursist.getResidency(), cursist.getStreet(),
                    "" + cursist.getNumber(), cursist.getPostalCode(),
                    cursist.getGender(), cursist.getPassword(), cursist.getPassword())
                        .validate();

            if (validator.size() > 0) return false;

            query.setString(1, cursist.getEmail());
            query.setString(2, cursist.getFirstName());
            query.setString(3, cursist.getLastName());
            query.setDate(4, new java.sql.Date(cursist.getBirthDay().getTime()));
            query.setString(5, cursist.getGender());
            query.setString(6, cursist.getPassword());
            query.setString(7, cursist.getStreet());
            query.setInt(8, cursist.getNumber());
            query.setString(9, cursist.getPostalCode());
            query.setString(10, cursist.getResidency());
            query.setString(11, cursist.getCountry());

            query.execute();

            return true;
        } catch (SQLException error) {
            return false;
        }
    }

    @Override
    public boolean update(int id, Cursist cursist) {

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("UPDATE Cursist " +
                    "SET EMail=?, " +
                    "FirstName=?, " +
                    "LastName=?, " +
                    "BirthDay=?, " +
                    "Geslacht=?, " +
                    "Street=?, " +
                    "Number=?, " +
                    "PostalCode=?, " +
                    "Residency=?, " +
                    "Country=? " +
                    "WHERE CursistId=?");


            List<String> validator = new UpdateCursistHandler(cursist.getFirstName(), cursist.getLastName(), cursist.getEmail(),
                    cursist.getStreet(), cursist.getResidency(), cursist.getStreet(),
                    "" + cursist.getNumber(), cursist.getPostalCode(), cursist.getGender())
                    .validate();

            if (validator.size() > 0) return false;

            query.setString(1, cursist.getEmail());
            query.setString(2, cursist.getFirstName());
            query.setString(3, cursist.getLastName());
            query.setDate(4, new java.sql.Date(cursist.getBirthDay().getTime()));
            query.setString(5, cursist.getGender());
            query.setString(6, cursist.getStreet());
            query.setInt(7, cursist.getNumber());
            query.setString(8, cursist.getPostalCode());
            query.setString(9, cursist.getResidency());
            query.setString(10, cursist.getCountry());
            query.setInt(11, id);

            query.execute();

            return true;
        } catch (SQLException error) {
            return false;
        }

    }

    @Override
    public boolean delete(Cursist cursist) {

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("DELETE FROM Cursist " +
                    "WHERE CursistID=?");

            query.setInt(1, cursist.getCursistId());

            query.execute();

            return true;
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't delete user", error.getMessage());
            return false;
        }
    }

    public boolean deleteByEmail(String email) {
        Cursist cursist = getByEmail(email);

        if (cursist == null) {
            return false;
        }

        try {
            Connection connection = databaseConnect.getConnection();
            PreparedStatement query = connection.prepareStatement("DELETE FROM Cursist " +
                    "WHERE email=?");

            query.setString(1, email);

            query.execute();

            return true;
        } catch (SQLException error) {
            return false;
        }
    }
}
