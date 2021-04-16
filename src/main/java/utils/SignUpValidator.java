package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignUpValidator {

    String firstName;
    String lastName;
    String email;
    String street;
    String city;
    String country;
    String homeNumber;
    String postalCode;
    String gender;
    String password;
    String retypedPassword;

    public SignUpValidator(String firstName, String lastName, String email, String street, String city, String country, String homeNumber, String postalCode, String gender, String password, String retypedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.country = country;
        this.homeNumber = homeNumber;
        this.postalCode = postalCode;
        this.gender = gender;
        this.password = password;
        this.retypedPassword = retypedPassword;
    }

    public List<String> validate() {
        List<String> toReturn = new ArrayList<>();

        if (firstName.isEmpty()) toReturn.add("First name is empty");
        if (lastName.isEmpty()) toReturn.add("Last name is empty");

        if (email.isEmpty()) toReturn.add("Email is empty");
        if (!email.matches("[\\w\\d]{1,}@[\\w\\d]{1,}.[\\w\\d]{1,}")) toReturn.add("E-Mail doesn't match pattern x@y.z");

        if (street.isEmpty()) toReturn.add("Street is empty");

        if (city.isEmpty()) toReturn.add("City is empty");

        if (country.isEmpty()) toReturn.add("Country is empty");

        if (homeNumber.isEmpty()) toReturn.add("Home number is empty");

        if (postalCode.isEmpty()) toReturn.add("Postal code is empty");
        if (!postalCode.matches("(\\d{4}) ?(\\w{2})")) toReturn.add("Postal code doesn't match pattern 9999AA or 9999 AA");

        if (gender.isEmpty()) toReturn.add("Gender is empty");

        if (password.isEmpty()) toReturn.add("Password is empty");

        if (retypedPassword.isEmpty()) toReturn.add("Retype password is empty");

        return toReturn;
    }
}
