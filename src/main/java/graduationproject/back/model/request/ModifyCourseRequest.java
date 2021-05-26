package graduationproject.back.model.request;

/**
 * @Author Pan
 * @Date 2020/12/1 16:37
 */
public class ModifyCourseRequest {
    int courseId;
    String authorId;
    String courseName;
    String courseDesc;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public boolean checkJson(){
        if(courseId == 0){
            return false;
        }
        return true;
    }
}
