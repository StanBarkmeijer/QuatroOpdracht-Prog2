package datastorage;

import domain.Address;
import domain.Cursist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class CursistDAO {

    /**
     * Return the Cursist from the the logged in cache
     * @return Cursist
     * @throws SQLException SQL Exception
     */
    public static Cursist getLoggedInCursist() throws SQLException {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        int id = Preferences.userRoot().getInt("user", 0);

        if (id == 0) {
            return null;
        }

        String query = String.format("SELECT * FROM Cursist WHERE ID=%s", id);
        ResultSet rs = databaseConnect.getConnection().prepareStatement(query).executeQuery();

        rs.next();

        return new Cursist(rs);
    }

    /**
     * Return the Address with the email and password
     * @param email The Cursist's email
     * @param password The Cursist's password
     * @return Cursist
     * @throws SQLException SQL Exception
     */
    public static Cursist getCursistFromEmailAndPassword(String email, String password) throws SQLException {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        String query = String.format("SELECT * FROM Cursist WHERE EMail='%s' AND Password='%s'", email, password);
        ResultSet rs = databaseConnect.getConnection().prepareStatement(query).executeQuery();

        rs.next();

        return new Cursist(rs);
    }

    /**
     * Return the Address from the Cursist ID
     * @param id The Cursist's ID
     * @return Cursist
     * @throws SQLException SQL Exception
     */
    public static Cursist getCursistFromID(int id) throws SQLException {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        String query = "SELECT * FROM Cursist WHERE ID=" + id;
        ResultSet rs = databaseConnect.getConnection().prepareStatement(query).executeQuery();

        rs.next();

        return new Cursist(rs);
    }

}
