package graduationproject.back.service.impl;

import graduationproject.back.mapper.AssignmentMapper;
import graduationproject.back.model.entity.Assignment;
import graduationproject.back.model.entity.Course;
import graduationproject.back.model.entity.Files;
import graduationproject.back.model.request.AdminCreateAssignmentRequest;
import graduationproject.back.model.request.AdminModifyAssignmentRequest;
import graduationproject.back.model.request.CreateAssignmentRequest;
import graduationproject.back.model.request.ModifyAssignmentRequest;
import graduationproject.back.model.vo.*;
import graduationproject.back.service.AssignmentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/24 20:43
 */
@Service
public class AssignmentServiceImp implements AssignmentService {
    private final AssignmentMapper assignmentMapper;

    public AssignmentServiceImp(AssignmentMapper assignmentMapper) {
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    public int createAssignment(String userId, CreateAssignmentRequest createAssignmentRequest) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentName(createAssignmentRequest.getAssignmentName());
        assignment.setAssignmentDesc(createAssignmentRequest.getAssignmentDesc());
        assignment.setAuthorId(userId);
        assignment.setCourseId(createAssignmentRequest.getCourseId());
        assignment.setCreateTime(new Date());

        return assignmentMapper.createAssignment(assignment);
    }

    @Override
    public List<GetMyAssignmentVo> getMyAssignment(String userId) {
        return assignmentMapper.getMyAssignment(userId);
    }

    @Override
    public int modifyAssignment(String userId, ModifyAssignmentRequest modifyAssignmentRequest) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentName(modifyAssignmentRequest.getAssignmentName());
        assignment.setAssignmentDesc(modifyAssignmentRequest.getAssignmentDesc());
        assignment.setAuthorId(userId);
        assignment.setAssignmentId(modifyAssignmentRequest.getAssignmentId());
        assignment.setCreateTime(new Date());
        System.out.println(assignment.toString());
        return assignmentMapper.modifyAssignment(assignment);
    }

    @Override
    public List<GetHandedListVo> getHandedList(Integer assignmentId) {
        return assignmentMapper.getHandedList(assignmentId);
    }

    @Override
    public List<Files> getStuFileList(String userId, Integer assignmentId) {
        return assignmentMapper.getStuFileList(userId,assignmentId);
    }

    @Override
    public List<GetUnsubmittedListVo> getUnsubmittedList(Integer assignmentId) {
        return assignmentMapper.GetUnsubmittedList(assignmentId);
    }

    @Override
    public List<getAssignmentByCourseIdVo> getAssignmentByCourseId(String userId, Integer courseId) {
        return assignmentMapper.getAssignmentByCourseId(userId,courseId);
    }

    @Override
    public List<getAssignmentVo> getAssignment(String userId) {
        List<getAssignmentVo> list = assignmentMapper.getAssignment(userId);
        return assignmentMapper.getAssignment(userId);
    }

    @Override
    public List<getSubmitAssignmentVo> getSubmitAssignment(String userId) {
        return assignmentMapper.getSubmitAssignment(userId);
    }

    @Override
    public int deleteWork(String userId, Integer assignmentId) {
        return assignmentMapper.deleteWork(userId,assignmentId);
    }

    @Override
    public List<GetMyAssignmentVo> AdminGetAssignment() {
        return assignmentMapper.AdminGetAssignment();
    }

    @Override
    public int adminModifyAssignment(AdminModifyAssignmentRequest adminModifyAssignmentRequest) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentName(adminModifyAssignmentRequest.getAssignmentName());
        assignment.setAssignmentDesc(adminModifyAssignmentRequest.getAssignmentDesc());
        assignment.setAuthorId(adminModifyAssignmentRequest.getAuthorId());
        assignment.setAssignmentId(adminModifyAssignmentRequest.getAssignmentId());
        assignment.setCreateTime(new Date());
        System.out.println(assignment.toString());
        return assignmentMapper.modifyAssignment(assignment);
    }

    @Override
    public List<AdminGetTeacherList> getCourseTeacherList() {
        return assignmentMapper.getCourseTeacherList();
    }

    @Override
    public List<Course>  getCourseListByTeacher(String authorId) {
        return assignmentMapper.getCourseListByTeacher(authorId);
    }

    @Override
    public int adminCreateCourse(AdminCreateAssignmentRequest adminCreateAssignmentRequest) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentName(adminCreateAssignmentRequest.getAssignmentName());
        assignment.setAssignmentDesc(adminCreateAssignmentRequest.getAssignmentDesc());
        assignment.setAuthorId(adminCreateAssignmentRequest.getAuthorId());
        assignment.setCourseId(adminCreateAssignmentRequest.getCourseId());
        assignment.setCreateTime(new Date());

        return assignmentMapper.createAssignment(assignment);
    }
}
