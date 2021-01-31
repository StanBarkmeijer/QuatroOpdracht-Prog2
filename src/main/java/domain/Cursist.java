package domain;

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
    private String street;
    private int number;
    private String postalCode;
    private String residency;
    private String country;

    public Cursist(int cursistId,
                   String email,
                   String firstName,
                   String lastName,
                   Date birthDay,
                   String gender,
                   String street,
                   int number,
                   String postalCode,
                   String residency,
                   String country) {
        this.cursistId = cursistId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.residency = residency;
        this.country = country;
    }

    /**
     * Cursist constructor from a SQL ResultSet
     *
     * @param rs SQL ResultSet
     * @throws SQLException SQL Exception
     */
    public Cursist(ResultSet rs) throws SQLException {
        this.cursistId = rs.getInt("cursistId");
        this.email = rs.getString("Email");
        this.firstName = rs.getString("FirstName");
        this.lastName = rs.getString("LastName");
        this.birthDay = rs.getDate("BirthDay");
        this.street = rs.getString("Street");
        this.number = rs.getInt("Number");
        this.postalCode = rs.getString("PostalCode");
        this.residency = rs.getString("Residency");
        this.country = rs.getString("Country");
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

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getResidency() {
        return residency;
    }

    public String getCountry() {
        return country;
    }
}