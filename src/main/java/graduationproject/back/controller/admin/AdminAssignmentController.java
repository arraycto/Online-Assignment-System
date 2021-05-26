package graduationproject.back.controller.admin;

import graduationproject.back.model.entity.Course;
import graduationproject.back.model.entity.Files;
import graduationproject.back.model.request.*;
import graduationproject.back.model.vo.AdminGetTeacherList;
import graduationproject.back.model.vo.GetHandedListVo;
import graduationproject.back.model.vo.GetMyAssignmentVo;
import graduationproject.back.model.vo.GetUnsubmittedListVo;
import graduationproject.back.service.AssignmentService;
import graduationproject.back.service.CommentService;
import graduationproject.back.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Pan
 * @Date 2021/5/8 12:27
 */
@RestController
@RequestMapping("api/admin/assignment")
public class AdminAssignmentController {
    private final CommentService commentService;
    private final AssignmentService assignmentService;

    public AdminAssignmentController(CommentService commentService, AssignmentService assignmentService) {
        this.commentService = commentService;
        this.assignmentService = assignmentService;
    }

    /**
     * 创建课程
     * @param request
     * @param
     * @return
     */
    @PostMapping("adminCreateCourse")
    public JsonData AdminCreateCourse(@RequestBody AdminCreateAssignmentRequest adminCreateAssignmentRequest){

        int result = assignmentService.adminCreateCourse(adminCreateAssignmentRequest);

        return result == 1 ? JsonData.buildSuccess("创建成功") : JsonData.buildError("创建失败");
    }

    /**
     * 获得所有作业列表
     * @return
     */
    @GetMapping("adminGetAssignment")
    public JsonData AdminGetAssignment(){

        List<GetMyAssignmentVo> list = assignmentService.AdminGetAssignment();

        return list != null ? JsonData.buildSuccess(list,"查询成功") : JsonData.buildError("查询失败");
    }

    /**
     * TODO 忘了这个有没有用了 😓 待排查
     * @param authorId
     * @return
     */
    @GetMapping("getMyAssignment")
    public JsonData getMyAssignment(@RequestParam(value = "authorId",required = true)String authorId){

        List<GetMyAssignmentVo> list = assignmentService.getMyAssignment(authorId);

        return list != null ? JsonData.buildSuccess(list,"查询成功") : JsonData.buildError("查询失败");
    }

    /**
     * 修改课程信息
     * @param request
     * @param modifyCourseRequest
     * @return
     */
    @PostMapping("modifyAssignment")
    public JsonData modifyAssignment(@RequestBody AdminModifyAssignmentRequest adminModifyAssignmentRequest){
        int result = assignmentService.adminModifyAssignment(adminModifyAssignmentRequest);
        return result == 1 ? JsonData.buildSuccess("修改成功") : JsonData.buildError("修改失败");
    }

    /**
     * 删除作业
     * @param authorId
     * @param assignmentId
     * @return
     */
    @PostMapping("deleteWork")
    public JsonData deleteWork(@RequestParam(value = "authorId",required = true)String authorId, @RequestParam(value = "assignmentId",required = true)Integer assignmentId){
        int result = assignmentService.deleteWork(authorId,assignmentId);
        return result != 0 ? JsonData.buildSuccess("删除成功") : JsonData.buildError("删除失败");
    }

    /**
     * 获得已经创建了课程的教师列表
     * 用于下面getCourseListByTeacher的使用
     * @return
     */
    @PostMapping("getCourseTeacherList")
    public JsonData getAllTeacher(){

        List<AdminGetTeacherList> list= assignmentService.getCourseTeacherList();

        return list !=null ? JsonData.buildSuccess(list,"创建成功") : JsonData.buildError("创建失败");
    }

    /**
     * 根据教师ID获得课程列表
     * @param authorId
     * @return
     */
    @PostMapping("getCourseListByTeacher")
    public JsonData getCourseListByTeacher(@RequestParam(value = "authorId",required = true)String authorId){

        List<Course> list = assignmentService.getCourseListByTeacher(authorId);

        return list !=null ? JsonData.buildSuccess(list,"创建成功") : JsonData.buildError("创建失败");
    }
}
