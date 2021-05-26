package graduationproject.back.model.request;

/**
 * @Author Pan
 * @Date 2020/12/1 16:15
 */
public class CreateCourseRequest {

    String courseName;
    String courseDesc;

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
        if(courseName == null||courseDesc == null){
            return false;
        }
        return true;
    }
}
