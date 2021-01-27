package datastorage;

import domain.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO {

    /**
     * Return the Address from the AddressID
     * @param id AddressID
     * @return Address
     * @throws SQLException SQL Exception
     */
    public static Address getAddress(int id) throws SQLException {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        String query = "SELECT * FROM Address WHERE AddressID=" + id;
        ResultSet rs = databaseConnect.getConnection().prepareStatement(query).executeQuery();

        rs.next();

        return new Address(rs);
    }

}
