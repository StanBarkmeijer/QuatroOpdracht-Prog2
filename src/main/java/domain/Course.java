package domain;

import datastorage.ContentDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Course {

    private int courseId;
    private String courseTitle;
    private String courseSubject;
    private String courseIntroduction;
    private String courseNiveau;
    private List<Content> contentList;

    public Course(int courseId, String courseTitle, String courseSubject, String courseIntroduction, String courseNiveau) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseSubject = courseSubject;
        this.courseIntroduction = courseIntroduction;
        this.courseNiveau = courseNiveau;
    }

    public Course(ResultSet rs) throws SQLException {
        ContentDAO contentDAO = new ContentDAO();

        this.courseId = rs.getInt("cursusId");
        this.courseTitle = rs.getString("cursusNaam");
        this.courseSubject = rs.getString("subject");
        this.courseIntroduction = rs.getString("introductionText");
        this.courseNiveau = rs.getString("niveau");

        this.contentList = contentDAO.getAllFromCourseId(courseId);
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

    public List<Content> getContentList() {
        return contentList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseSubject='" + courseSubject + '\'' +
                ", courseIntroduction='" + courseIntroduction + '\'' +
                ", courseNiveau='" + courseNiveau + '\'' +
                ", contentList=" + contentList +
                '}';
    }
}
