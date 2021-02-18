package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public abstract class Content {

    private int contentId;
    private int cursusId;
    private Date publication;
    private String status;
    private String title;

    public Content(ResultSet rs) throws SQLException {
        this.contentId = rs.getInt("contentId");
        this.cursusId = rs.getInt("cursusId");
        this.publication = rs.getDate("publication");
        this.status = rs.getString("status");
        this.status = rs.getString("title");
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
}
