package graduationproject.back.model.request;

/**
 * @Author Pan
 * @Date 2021/5/7 19:20
 */
public class AdminCreateCourseRequest {
    String authorId;
    String courseName;
    String courseDesc;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }
}
