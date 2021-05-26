package graduationproject.back.controller.teacher;

import graduationproject.back.model.entity.Files;
import graduationproject.back.model.request.*;
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
 * @Date 2020/12/24 20:39
 */
@RestController
@RequestMapping("api/teacher/assignment")
public class TeacherAssignmentController {

    private final CommentService commentService;
    private final AssignmentService assignmentService;

    public TeacherAssignmentController(AssignmentService assignmentService, CommentService commentService) {
        this.assignmentService = assignmentService;
        this.commentService = commentService;
    }

    /**
     * 创建课程
     * @param request
     * @param
     * @return
     */
    @PostMapping("createAssignment")
    public JsonData createCourse(HttpServletRequest request, @RequestBody CreateAssignmentRequest CreateAssignmentRequest){

        String userId = (String) request.getAttribute("userId");

        int result = assignmentService.createAssignment(userId,CreateAssignmentRequest);

        return result == 1 ? JsonData.buildSuccess("创建成功") : JsonData.buildError("创建失败");
    }

    /**
     * 获得我的课程列表
     * @param request
     * @return
     */
    @GetMapping("getMyAssignment")
    public JsonData getMyAssignment(HttpServletRequest request){

        String userId = (String) request.getAttribute("userId");

        List<GetMyAssignmentVo> list = assignmentService.getMyAssignment(userId);

        return list != null ? JsonData.buildSuccess(list,"查询成功") : JsonData.buildError("查询失败");
    }

    /**
     * 修改课程信息
     * @param request
     * @param modifyCourseRequest
     * @return
     */
    @PostMapping("modifyAssignment")
    public JsonData modifyAssignment(HttpServletRequest request, @RequestBody ModifyAssignmentRequest modifyAssignmentRequest){
        String userId = (String) request.getAttribute("userId");
        int result = assignmentService.modifyAssignment(userId,modifyAssignmentRequest);
        return result == 1 ? JsonData.buildSuccess("修改成功") : JsonData.buildError("修改失败");
    }

    /**
     * 获得已提交作业学生列表
     * @param assignmentId
     * @return
     */
    @GetMapping("getHandedList")
    public JsonData getHandedList(@RequestParam(value = "assignmentId",required = true)Integer assignmentId){


        List<GetHandedListVo> list = assignmentService.getHandedList(assignmentId);
        System.out.println(list.toString());
        return list != null ? JsonData.buildSuccess(list,"查询成功") : JsonData.buildError("查询失败");
    }

    /**
     * 获得未提及作业学生列表
     * @param assignmentId
     * @return
     */
    @GetMapping("getUnsubmittedList")
    public JsonData getUnsubmittedList(@RequestParam(value = "assignmentId",required = true)Integer assignmentId){


        List<GetUnsubmittedListVo> list = assignmentService.getUnsubmittedList(assignmentId);

        return list != null ? JsonData.buildSuccess(list,"查询成功") : JsonData.buildError("查询失败");
    }

    /**
     * 获得学生作业列表
     * @param assignmentId
     * @param userId
     * @return
     */
    @GetMapping("getStuFileList")
    public JsonData getStuFileList(@RequestParam(value = "assignmentId",required = true)Integer assignmentId,@RequestParam(value = "userId",required = true)String userId){

        List<Files> list = assignmentService.getStuFileList(userId,assignmentId);

        return list != null ? JsonData.buildSuccess(list,"查询成功") : JsonData.buildError("查询失败");
    }

    /**
     * 评论学生作业
     * @param request
     * @param CommentAssignmentRequest
     * @return
     */
    @PostMapping("commentAssignment")
    public JsonData commentAssignment(HttpServletRequest request, @RequestBody CommentAssignmentRequest CommentAssignmentRequest){
        String userId = (String) request.getAttribute("userId");
        int result = commentService.commentAssignment(userId,CommentAssignmentRequest);
        return result == 1 ||result ==2 ? JsonData.buildSuccess("添加成功") : JsonData.buildError("添加失败");
    }

    /**
     * 删除作业
     * @param request
     * @param assignmentId
     * @return
     */
    @PostMapping("deleteWork")
    public JsonData deleteCourse(HttpServletRequest request, @RequestParam(value = "assignmentId",required = true)Integer assignmentId){
        String userId = (String) request.getAttribute("userId");
        int result = assignmentService.deleteWork(userId,assignmentId);
        return result != 0 ? JsonData.buildSuccess("删除成功") : JsonData.buildError("删除失败");
    }
}
