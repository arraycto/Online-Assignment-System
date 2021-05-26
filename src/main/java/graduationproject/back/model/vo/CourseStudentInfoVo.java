package graduationproject.back.model.vo;

import java.util.Date;

/**
 * @Author Pan
 * @Date 2020/12/13 22:50
 */
public class CourseStudentInfoVo {

    private Integer courseId;

    private String name;

    private String userId;

    private Date joinTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public Date getJoinTime() {

        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}
