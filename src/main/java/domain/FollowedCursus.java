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
    private int contentId;

    public FollowedCursus(int cursistId, int cursusId, String identifier, Date registrationDate, int contentId) {
        this.cursistId = cursistId;
        this.cursusId = cursusId;
        this.identifier = identifier;
        this.registrationDate = registrationDate;
        this.contentId = contentId;
    }

    public FollowedCursus(ResultSet rs) throws SQLException {
        this.followedCursusId = rs.getInt("FollowedCursusId");
        this.cursistId = rs.getInt("CursistId");
        this.cursusId = rs.getInt("CursusId");
        this.identifier = rs.getString("Identifier");
        this.registrationDate = rs.getDate("RegistrationDate");
        this.contentId = rs.getInt("ContentID");
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

    public int getContentId() {
        return contentId;
    }

    @Override
    public String toString() {
        return "FollowedCursus{" +
                "followedCursusId=" + followedCursusId +
                ", cursistId=" + cursistId +
                ", cursusId=" + cursusId +
                ", identifier='" + identifier + '\'' +
                ", registrationDate=" + registrationDate +
                ", contentId=" + contentId +
                '}';
    }
}
