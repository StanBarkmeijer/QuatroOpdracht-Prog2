package datastorage;

import domain.Address;
import domain.Cursist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CursistDAO {

    public static Cursist getCursistFromEmailAndPassword(String email, String password) throws SQLException {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        String query = String.format("SELECT * FROM Cursist WHERE EMail='%s' AND Password='%s'", email, password);
        ResultSet rs = databaseConnect.getConnection().prepareStatement(query).executeQuery();

        rs.next();

        return new Cursist(rs);
    }

    public static Cursist getCursistFromID(int id) throws SQLException {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        String query = "SELECT * FROM Cursist WHERE ID=" + id;
        ResultSet rs = databaseConnect.getConnection().prepareStatement(query).executeQuery();

        rs.next();

        return new Cursist(rs);
    }

}
