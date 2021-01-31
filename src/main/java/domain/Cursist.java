package domain;

import datastorage.AddressDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Cursist {

    private int cursistId;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String gender;
    private Address address;

    /**
     * Cursist constructor.
     * @param cursistId The cursist ID
     * @param email The cursist E-Mail
     * @param firstName The cursist first name
     * @param lastName The cursist last name
     * @param birthDay The cursist birthday
     * @param gender The cursist gender
     * @param address The cursist address
     */
    public Cursist(int cursistId,
                   String email,
                   String firstName,
                   String lastName,
                   Date birthDay,
                   String gender,
                   Address address) {
        this.cursistId = cursistId;
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
        this.cursistId = rs.getInt("cursistId");
        this.email = rs.getString("Email");
        this.firstName = rs.getString("FirstName");
        this.lastName = rs.getString("LastName");
        this.birthDay = rs.getDate("BirthDay");

        this.address = new AddressDAO().get(rs.getInt("AddressID"));
    }

    @Override
    public String toString() {
        return "User{" +
                "cursistId=" + cursistId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                '}';
    }

    public int getCursistId() {
        return cursistId;
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
