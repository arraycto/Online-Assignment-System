package graduationproject.back.model.vo;

/**
 * @Author Pan
 * @Date 2020/12/25 21:42
 */
public class GetHandedListVo {

    private String userId;
    private String name;
    private Integer assignmentId;
    private Integer courseId;
    private String commentContentStatus;
    private String commentScoreStatus;
    private String content;
    private String score;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
