package graduationproject.back.model.request;

/**
 * @Author Pan
 * @Date 2020/12/24 20:46
 */
public class CreateAssignmentRequest {
    private int courseId;
    private String assignmentName;
    private String assignmentDesc;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentDesc() {
        return assignmentDesc;
    }

    public void setAssignmentDesc(String assignmentDesc) {
        this.assignmentDesc = assignmentDesc;
    }
}
