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
    private Address addres;

    public Cursist(int id,
                   String email,
                   String firstName,
                   String lastName,
                   Date birthDay,
                   String gender,
                   Address addres) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.addres = addres;
    }

    public Cursist(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.email = rs.getString("Email");
        this.firstName = rs.getString("FirstName");
        this.lastName = rs.getString("LastName");
        this.birthDay = rs.getDate("BirthDay");

        this.addres = AddressDAO.getAddress(rs.getInt("AddressID"));
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
                ", addres=" + addres +
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

    public Address getAddres() {
        return addres;
    }
}
