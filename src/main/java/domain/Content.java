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
    private String type;

    public Content(ResultSet rs, String type) throws SQLException {
        this.contentId = rs.getInt("contentId");
        this.cursusId = rs.getInt("cursusId");
        this.publication = rs.getDate("publication");
        this.status = rs.getString("status");
        this.title = rs.getString("title");

        this.type = type;
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

    public String getType() {
        return type;
    }
}
