package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {

    private int courseId;
    private String courseTitle;
    private String courseSubject;
    private String courseIntroduction;
    private String courseNiveau;

    public Course(int courseId, String courseTitle, String courseSubject, String courseIntroduction, String courseNiveau) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseSubject = courseSubject;
        this.courseIntroduction = courseIntroduction;
        this.courseNiveau = courseNiveau;
    }

    public Course(ResultSet rs) throws SQLException {
        this.courseId = rs.getInt("cursusId");
        this.courseTitle = rs.getString("cursusNaam");
        this.courseSubject = rs.getString("subject");
        this.courseIntroduction = rs.getString("introductionText");
        this.courseNiveau = rs.getString("niveau");
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public String getCourseIntroduction() {
        return courseIntroduction;
    }

    public String getCourseNiveau() {
        return courseNiveau;
    }
}
