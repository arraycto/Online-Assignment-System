package graduationproject.back.service;

import graduationproject.back.model.entity.Course;
import graduationproject.back.model.entity.Files;
import graduationproject.back.model.request.AdminCreateAssignmentRequest;
import graduationproject.back.model.request.AdminModifyAssignmentRequest;
import graduationproject.back.model.request.CreateAssignmentRequest;
import graduationproject.back.model.request.ModifyAssignmentRequest;
import graduationproject.back.model.vo.*;

import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/24 20:43
 */
public interface AssignmentService {
    int createAssignment(String userId, CreateAssignmentRequest createAssignmentRequest);

    List<GetMyAssignmentVo> getMyAssignment(String userId);

    int modifyAssignment(String userId, ModifyAssignmentRequest modifyAssignmentRequest);

    List<GetHandedListVo> getHandedList(Integer assignmentId);

    List<Files> getStuFileList(String userId, Integer assignmentId);

    List<GetUnsubmittedListVo> getUnsubmittedList(Integer assignmentId);

    List<getAssignmentByCourseIdVo> getAssignmentByCourseId(String userId, Integer courseId);

    List<getAssignmentVo> getAssignment(String userId);

    List<getSubmitAssignmentVo> getSubmitAssignment(String userId);

    int deleteWork(String userId, Integer courseId);

    List<GetMyAssignmentVo> AdminGetAssignment();

    int adminModifyAssignment(AdminModifyAssignmentRequest adminModifyAssignmentRequest);

    List<AdminGetTeacherList> getCourseTeacherList();

    List<Course>  getCourseListByTeacher(String authorId);

    int adminCreateCourse(AdminCreateAssignmentRequest adminCreateAssignmentRequest);
}
