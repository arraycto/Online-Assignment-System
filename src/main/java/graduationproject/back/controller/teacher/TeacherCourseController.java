package graduationproject.back.controller.teacher;

import graduationproject.back.model.entity.Course;
import graduationproject.back.model.request.CreateCourseRequest;
import graduationproject.back.model.request.ModifyCourseRequest;
import graduationproject.back.model.vo.CourseStudentInfoVo;
import graduationproject.back.model.vo.CourseVo;
import graduationproject.back.service.CourseService;
import graduationproject.back.service.FileService;
import graduationproject.back.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/1 16:13
 */
@RestController
@RequestMapping("api/teacher/course")
public class TeacherCourseController {

    private final FileService fileService;
    private final CourseService courseService;

    public TeacherCourseController(CourseService courseService, FileService fileService) {
        this.courseService = courseService;
        this.fileService = fileService;
    }

    /**
     * 创建课程
     * @param request
     * @param createCourseRequest
     * @return
     */
    @PostMapping("createCourse")
    public JsonData createCourse(HttpServletRequest request, @RequestBody CreateCourseRequest createCourseRequest){

        String userId = (String) request.getAttribute("userId");
        int result = courseService.createCourse(userId,createCourseRequest);

        return result == 1 ? JsonData.buildSuccess("创建成功") : JsonData.buildError("创建失败");
    }

    /**
     * 修改课程信息
     * @param request
     * @param modifyCourseRequest
     * @return
     */
    @PostMapping("modifyCourse")
    public JsonData modifyCourse(HttpServletRequest request, @RequestBody ModifyCourseRequest modifyCourseRequest){
        String userId = (String) request.getAttribute("userId");
        int result = courseService.modifyCourse(userId,modifyCourseRequest);
        return result == 1 ? JsonData.buildSuccess("修改成功") : JsonData.buildError("修改失败");
    }

    /**
     * 删除课程
     * @param request
     * @param courseId
     * @return
     */
    @PostMapping("deleteCourse")
    public JsonData deleteCourse(HttpServletRequest request, @RequestParam(value = "courseId",required = true)Integer courseId){
        String userId = (String) request.getAttribute("userId");
        int result = courseService.deleteCourse(userId,courseId);
        return result !=0 ? JsonData.buildSuccess("删除成功") : JsonData.buildError("删除失败");
    }

    /**
     * 获取当前课程中的学生列表
     * @param courseId
     * @return
     */
    @GetMapping("getAllStudentInfo")
    public JsonData getAllStudentInfo(@RequestParam(value = "courseId",required = true)Integer courseId){
        List<CourseStudentInfoVo> list = courseService.getAllStudentInfo(courseId);

        return list != null ? JsonData.buildSuccess(list,"获取成功") : JsonData.buildError("获取失败");
    }
    /**
     * 获取所有的课程
     * @return
     */
    @GetMapping("getAllCourse")
    public JsonData teacherGetAllCourse(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        List<Course> list = courseService.teacherGetAllCourse(userId);
        return list != null ? JsonData.buildSuccess(list,"获取成功") : JsonData.buildError("获取失败");
    }

    /**
     * 将学生从课程中移除
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("deleteStudentOnCourse")
    public JsonData deleteStudentOnCourse(@RequestParam(value = "courseId",required = true)Integer courseId,@RequestParam(value = "userId",required = true)String userId){
        int result = courseService.deleteStudentOnCourse(userId,courseId);
        return result != 0 ? JsonData.buildSuccess("删除成功") : JsonData.buildError("删除失败");
    }
}
