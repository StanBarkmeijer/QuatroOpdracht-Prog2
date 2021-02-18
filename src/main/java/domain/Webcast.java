package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Webcast extends Content {

    private int duration;
    private String url;
    private String firstNameSpeaker;
    private String lastNameSpeaker;
    private String organisation;

    public Webcast(ResultSet rs) throws SQLException {
        super(rs);

        this.duration = rs.getInt("duration");
        this.url = rs.getString("url");
        this.firstNameSpeaker = rs.getString("firstNameSpeaker");
        this.lastNameSpeaker = rs.getString("lastNameSpeaker");
        this.organisation = rs.getString("organisation");
    }

    public int getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public String getFirstNameSpeaker() {
        return firstNameSpeaker;
    }

    public String getLastNameSpeaker() {
        return lastNameSpeaker;
    }

    public String getOrganisation() {
        return organisation;
    }
}
