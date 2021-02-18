package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Module extends Content {

    private int contentId;
    private int cursusId;
    private Date publication;
    private String status;
    private String title;
    private String version;
    private String description;
    private String firstNameContact;
    private String lastNameContact;

    public Module(Date publication, String status, String title, String version, String description, String firstNameContact, String lastNameContact) {
        this.publication = publication;
        this.status = status;
        this.title = title;
        this.version = version;
        this.description = description;
        this.firstNameContact = firstNameContact;
        this.lastNameContact = lastNameContact;
    }

    public Module(ResultSet rs) throws SQLException {
        this.contentId = rs.getInt("contentId");
        this.cursusId = rs.getInt("cursusId");
        this.publication = rs.getDate("publication");
        this.status = rs.getString("status");
        this.title = rs.getString("title");
        this.version = rs.getString("version");
        this.description = rs.getString("description");
        this.firstNameContact = rs.getString("firstNameContact");
        this.lastNameContact = rs.getString("lastNameContact");
    }

    public int getContentId() {
        return contentId;
    }

    public int getCursusId() {
        return cursusId;
    }

    public Date getPublication() {
        return publication;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
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
}
