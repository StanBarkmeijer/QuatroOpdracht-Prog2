package domain;

import datastorage.AddressDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Cursist {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String gender;
    private Address address;

    /**
     * Cursist constructor.
     * @param id The cursist ID
     * @param email The cursist E-Mail
     * @param firstName The cursist first name
     * @param lastName The cursist last name
     * @param birthDay The cursist birthday
     * @param gender The cursist gender
     * @param address The cursist address
     */
    public Cursist(int id,
                   String email,
                   String firstName,
                   String lastName,
                   Date birthDay,
                   String gender,
                   Address address) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.address = address;
    }

    /**
     * Cursist constructor from a SQL ResultSet
     * @param rs SQL ResultSet
     * @throws SQLException SQL Exception
     */
    public Cursist(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.email = rs.getString("Email");
        this.firstName = rs.getString("FirstName");
        this.lastName = rs.getString("LastName");
        this.birthDay = rs.getDate("BirthDay");

        this.address = AddressDAO.getAddress(rs.getInt("AddressID"));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }
}
