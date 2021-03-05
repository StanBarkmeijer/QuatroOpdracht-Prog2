package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Module extends Content {

    private String version;
    private String description;
    private String firstNameContact;
    private String lastNameContact;

    public Module(ResultSet rs) throws SQLException {
        super(rs, "Module");

        this.version = rs.getString("version");
        this.description = rs.getString("description");
        this.firstNameContact = rs.getString("firstNameContact");
        this.lastNameContact = rs.getString("lastNameContact");
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getFirstNameContact() {
        return firstNameContact;
    }

    public String getLastNameContact() {
        return lastNameContact;
    }

    @Override
    public String toString() {
        return "Module{" +
                "version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", firstNameContact='" + firstNameContact + '\'' +
                ", lastNameContact='" + lastNameContact + '\'' +
                '}';
    }
}
