package graduationproject.back.model.request;

import java.util.Date;

/**
 * @Author Pan
 * @Date 2021/5/9 22:52
 */
public class ChangeAccountDisableRequest {
    String userId;

    String name;

    String accountType;

    String accountDisabled;

    String courseDisabled;

    String assignmentDisabled;

    Date createTime;

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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountDisabled() {
        return accountDisabled;
    }

    public void setAccountDisabled(String accountDisabled) {
        this.accountDisabled = accountDisabled;
    }

    public String getCourseDisabled() {
        return courseDisabled;
    }

    public void setCourseDisabled(String courseDisabled) {
        this.courseDisabled = courseDisabled;
    }

    public String getAssignmentDisabled() {
        return assignmentDisabled;
    }

    public void setAssignmentDisabled(String assignmentDisabled) {
        this.assignmentDisabled = assignmentDisabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
