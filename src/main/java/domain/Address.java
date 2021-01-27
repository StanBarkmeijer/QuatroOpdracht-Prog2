package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {

    private int id;
    private String street;
    private int houseNumber;
    private String additional;
    private String postalCode;
    private String placeOfResidency;
    private String country;

    public Address(int id,
                   String street,
                   int houseNumber,
                   String additional,
                   String postalCode,
                   String placeOfResidency,
                   String country) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.additional = additional;
        this.postalCode = postalCode;
        this.placeOfResidency = placeOfResidency;
        this.country = country;
    }

    public Address(ResultSet rs) throws SQLException {
        this.id = rs.getInt("addressId");
        this.street = rs.getString("Street");
        this.houseNumber = rs.getInt("HouseNumber");
        this.additional = rs.getString("additional");
        this.postalCode = rs.getString("postalCode");
        this.placeOfResidency = rs.getString("placeOfResidency");
        this.country = rs.getString("country");
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", additional='" + additional + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", placeOfResidency='" + placeOfResidency + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getAdditional() {
        return additional;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPlaceOfResidency() {
        return placeOfResidency;
    }

    public String getCountry() {
        return country;
    }
}
