package graduationproject.back.model.request;

/**
 * @Author Pan
 * @Date 2020/12/24 23:12
 */
public class ModifyAssignmentRequest {

    int assignmentId;
    String assignmentName;
    String assignmentDesc;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
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
