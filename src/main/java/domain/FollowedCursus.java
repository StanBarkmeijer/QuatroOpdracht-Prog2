package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FollowedCursus {

    private int followedCursusId;
    private int cursistId;
    private int cursusId;
    private String identifier;
    private Date registrationDate;

    public FollowedCursus(int cursistId, int cursusId, String identifier, Date registrationDate) {
        this.cursistId = cursistId;
        this.cursusId = cursusId;
        this.identifier = identifier;
        this.registrationDate = registrationDate;
    }

    public FollowedCursus(ResultSet rs) throws SQLException {
        this.followedCursusId = rs.getInt("FollowedCursusId");
        this.cursistId = rs.getInt("CursistId");
        this.cursusId = rs.getInt("CursusId");
        this.identifier = rs.getString("Identifier");
        this.registrationDate = rs.getDate("RegistrationDate");
    }

    public int getFollowedCursusId() {
        return followedCursusId;
    }

    public int getCursistId() {
        return cursistId;
    }

    public int getCursusId() {
        return cursusId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
}
