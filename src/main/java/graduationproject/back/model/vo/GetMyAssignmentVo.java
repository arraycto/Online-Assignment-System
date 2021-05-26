package graduationproject.back.model.vo;

import java.util.Date;

/**
 * @Author Pan
 * @Date 2020/12/25 10:58
 */
public class GetMyAssignmentVo {
    private Integer assignmentId;

    private Integer courseId;

    private String authorId;

    private String assignmentName;

    private String assignmentDesc;

    private Date createTime;

    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId == null ? null : authorId.trim();
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName == null ? null : assignmentName.trim();
    }

    public String getAssignmentDesc() {
        return assignmentDesc;
    }

    public void setAssignmentDesc(String assignmentDesc) {
        this.assignmentDesc = assignmentDesc == null ? null : assignmentDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", courseId=" + courseId +
                ", authorId='" + authorId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", assignmentDesc='" + assignmentDesc + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
