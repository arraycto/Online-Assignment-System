package graduationproject.back.controller.student;

import graduationproject.back.model.vo.CourseVo;
import graduationproject.back.model.vo.getAssignmentByCourseIdVo;
import graduationproject.back.model.vo.getAssignmentVo;
import graduationproject.back.model.vo.getSubmitAssignmentVo;
import graduationproject.back.service.AssignmentService;
import graduationproject.back.service.impl.AssignmentServiceImp;
import graduationproject.back.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/26 21:06
 */
@RestController
@RequestMapping("api/student/assignment")
public class StudentAssignmentController {

    private final AssignmentService assignmentService;

    public StudentAssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    /**
     * 获取所有的作业根据课程ID
     * @return
     */
    @GetMapping("getAssignmentByCourseId")
    public JsonData getAssignmentByCourseId(HttpServletRequest request,@RequestParam(value = "courseId",required = true)Integer courseId){
        String userId = (String) request.getAttribute("userId");

        List<getAssignmentByCourseIdVo> List = assignmentService.getAssignmentByCourseId(userId,courseId);
        return JsonData.buildSuccess(List);
    }

    /**
     * 获取所有的作业
     * @return
     */
    @GetMapping("getAssignment")
    public JsonData getAssignment(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");

        List<getAssignmentVo> List = assignmentService.getAssignment(userId);
        return JsonData.buildSuccess(List);
    }

    /**
     * 获取所有已提交的作业
     * @return
     */
    @GetMapping("getSubmitAssignment")
    public JsonData getSubmitAssignment(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");

        List<getSubmitAssignmentVo> List = assignmentService.getSubmitAssignment(userId);
        return JsonData.buildSuccess(List);
    }
}
