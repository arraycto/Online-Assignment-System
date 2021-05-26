package graduationproject.back.mapper;

import graduationproject.back.model.entity.Assignment;
import graduationproject.back.model.entity.Course;
import graduationproject.back.model.entity.Files;
import graduationproject.back.model.vo.*;

import java.util.List;

public interface AssignmentMapper {

    int createAssignment(Assignment assignment);

    List<GetMyAssignmentVo> getMyAssignment(String userId);

    int modifyAssignment(Assignment assignment);

    List<GetHandedListVo> getHandedList(Integer assignmentId);

    List<Files> getStuFileList(String userId, Integer assignmentId);

    List<GetUnsubmittedListVo> GetUnsubmittedList(Integer assignmentId);

    List<getAssignmentByCourseIdVo> getAssignmentByCourseId(String userId, Integer courseId);

    List<getAssignmentVo> getAssignment(String userId);

    List<getSubmitAssignmentVo> getSubmitAssignment(String userId);

    int deleteWork(String userId, Integer assignmentId);

    List<GetMyAssignmentVo> AdminGetAssignment();

    List<AdminGetTeacherList> getCourseTeacherList();

    List<Course>  getCourseListByTeacher(String authorId);
}