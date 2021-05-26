package graduationproject.back.controller.admin;

import graduationproject.back.model.entity.Course;
import graduationproject.back.model.request.AdminCreateCourseRequest;
import graduationproject.back.model.request.CreateCourseRequest;
import graduationproject.back.model.request.ModifyCourseRequest;
import graduationproject.back.model.vo.AdminGetAllCourseVo;
import graduationproject.back.model.vo.AdminGetTeacherList;
import graduationproject.back.model.vo.CourseStudentInfoVo;
import graduationproject.back.model.vo.GetUserInfoVo;
import graduationproject.back.service.CourseService;
import graduationproject.back.service.FileService;
import graduationproject.back.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Pan
 * @Date 2021/5/7 18:06
 */
@RestController
@RequestMapping("api/admin/course")
public class AdminCourseController {
    private final FileService fileService;
    private final CourseService courseService;

    public AdminCourseController(FileService fileService, CourseService courseService) {
        this.fileService = fileService;
        this.courseService = courseService;
    }


    /**
     * 创建课程
     * @param request
     * @param createCourseRequest
     * @return
     */
    @PostMapping("createCourse")
    public JsonData createCourse(@RequestBody AdminCreateCourseRequest adminCreateCourseRequest){

        int result = courseService.adminCreateCourse(adminCreateCourseRequest.getAuthorId(),adminCreateCourseRequest);

        return result == 1 ? JsonData.buildSuccess("创建成功") : JsonData.buildError("创建失败");
    }

    /**
     * 获得教师列表
     * @return
     */
    @PostMapping("getTeacherList")
    public JsonData getAllTeacher(){

        List<AdminGetTeacherList> list= courseService.getTeacherList();

        return list !=null ? JsonData.buildSuccess(list,"创建成功") : JsonData.buildError("创建失败");
    }

    /**
     * 修改课程信息
     * @param request
     * @param modifyCourseRequest
     * @return
     */
    @PostMapping("modifyCourse")
    public JsonData modifyCourse(@RequestBody ModifyCourseRequest modifyCourseRequest){

        int result = courseService.modifyCourse(modifyCourseRequest.getAuthorId(),modifyCourseRequest);
        return result == 1 ? JsonData.buildSuccess("修改成功") : JsonData.buildError("修改失败");
    }

    /**
     * 删除课程
     * @param request
     * @param courseId
     * @return
     */
    @PostMapping("deleteCourse")
    public JsonData deleteCourse(@RequestParam(value = "teacherId",required = true)String teacherId, @RequestParam(value = "courseId",required = true)Integer courseId){
        int result = courseService.deleteCourse(teacherId,courseId);
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
    public JsonData teacherGetAllCourse(){
        List<AdminGetAllCourseVo> list = courseService.adminGetAllCourse();
        return list != null ? JsonData.buildSuccess(list,"获取成功") : JsonData.buildError("获取失败");
    }
}
