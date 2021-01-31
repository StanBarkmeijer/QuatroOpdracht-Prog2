package datastorage;

import domain.Address;
import domain.Cursist;
import javafx.scene.control.Alert;
import utils.ResponseHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements DAO<Address> {

    private DatabaseConnect databaseConnect;

    public AddressDAO() {
        this.databaseConnect = new DatabaseConnect();
    }

    @Override
    public List<Address> getAll() {
        ArrayList<Address> list = new ArrayList<>();

        try {
            Connection connection = databaseConnect.getConnection();

            final String query = "SELECT * FROM Address";

            ResultSet rs = connection.prepareStatement(query).executeQuery(query);

            while (rs.next()) {
                list.add(new Address(rs));
            }
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get all addresses", error.getMessage());
        }

        return list;
    }

    @Override
    public Address get(int id) {
        Address address = null;

        try {
            Connection connection = databaseConnect.getConnection();

            final String query = "SELECT * FROM Address WHERE AddressID=" + id;

            ResultSet rs = connection.prepareStatement(query).executeQuery(query);

            rs.next();

            address = new Address(rs);
        } catch (SQLException error) {
            ResponseHandler.handleError(Alert.AlertType.ERROR, "Couldn't get address with ID: " + id, error.getMessage());
        }

        return address;
    }

    @Override
    public void save(Address address) {

    }

    @Override
    public void update(Address address, String[] params) {

    }

    @Override
    public void delete(Address address) {

    }
}
