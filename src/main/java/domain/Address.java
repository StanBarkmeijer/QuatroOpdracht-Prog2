package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {

    private int addressId;
    private String street;
    private int number;
    private String addition;
    private String postalCode;
    private String residency;
    private String country;

    /**
     * Address constructor
     * @param addressId The address ID
     * @param street The street
     * @param number The house number
     * @param addition The address additional
     * @param postalCode The postal code
     * @param residency The residency
     * @param country The country
     */
    public Address(int addressId,
                   String street,
                   int number,
                   String addition,
                   String postalCode,
                   String residency,
                   String country) {
        this.addressId = addressId;
        this.street = street;
        this.number = number;
        this.addition = addition;
        this.postalCode = postalCode;
        this.residency = residency;
        this.country = country;
    }

    /**
     * Address constructor from a SQL ResultSet
     * @param rs rs SQL ResultSet
     * @throws SQLException SQL Exception
     */
    public Address(ResultSet rs) throws SQLException {
        this.addressId = rs.getInt("addressId");
        this.street = rs.getString("Street");
        this.number = rs.getInt("number");
        this.addition = rs.getString("addition");
        this.postalCode = rs.getString("postalCode");
        this.residency = rs.getString("residency");
        this.country = rs.getString("country");
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", addition='" + addition + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", residency='" + residency + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public int getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getAddition() {
        return addition;
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
