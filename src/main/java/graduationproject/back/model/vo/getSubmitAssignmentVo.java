package graduationproject.back.model.vo;

/**
 * @Author Pan
 * @Date 2020/12/27 23:18
 */
public class getSubmitAssignmentVo {
    private Integer assignmentId;
    private Integer courseId;
    private String courseName;
    private String assignmentName;
    private String assignmentDesc;
    private Integer submitStatus;
    private String commentContentStatus;
    private String commentScoreStatus;
    private String content;
    private String score;

    public String getCommentContentStatus() {

        return commentContentStatus;
    }

    public void setCommentContentStatus(String commentContentStatus) {
        this.commentContentStatus = commentContentStatus;
    }

    public String getCommentScoreStatus() {
        return commentScoreStatus;
    }

    public void setCommentScoreStatus(String commentScoreStatus) {
        this.commentScoreStatus = commentScoreStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public Integer getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(Integer submitStatus) {
        this.submitStatus = submitStatus;
    }
}
