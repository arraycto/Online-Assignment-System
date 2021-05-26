package graduationproject.back.controller.student;

import graduationproject.back.model.entity.Course;
import graduationproject.back.model.vo.CourseVo;
import graduationproject.back.model.vo.GetAllMyCourseVo;
import graduationproject.back.service.CourseService;
import graduationproject.back.service.FileService;
import graduationproject.back.utils.FileUtil;
import graduationproject.back.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/1 14:40
 */
@RestController
@RequestMapping("api/student/course")
public class StudentCourseController {

    private final FileService fileService;

    private final CourseService courseService;

    public StudentCourseController(CourseService courseService, FileService fileService) {
        this.courseService = courseService;
        this.fileService = fileService;
    }

    /**
     * 获取所有的课程
     * @return
     */
    @GetMapping("getAllCourse")
    public JsonData getAllCourse(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        List<CourseVo> courseList = courseService.getAllCourse(userId);
        return JsonData.buildSuccess(courseList);
    }

    /**
     * 加入课程
     * @param request
     * @param courseId
     * @return
     */
    @PostMapping("joinCourse")
    public JsonData joinCourse(HttpServletRequest request, @RequestParam(value = "courseId",required = true)Integer courseId){
        String userId = (String) request.getAttribute("userId");

        int result = courseService.joinCourse(userId, courseId);
        return result == 1 ? JsonData.buildSuccess("加入成功") : JsonData.buildError("加入失败");
    }

    /**
     * 获取所有我的课程
     * @return
     */
    @GetMapping("getAllMyCourse")
    public JsonData getAllMyCourse(HttpServletRequest request){
        String userId = (String) request.getAttribute("userId");
        List<GetAllMyCourseVo> courseList = courseService.getAllMyCourse(userId);
        return JsonData.buildSuccess(courseList);
    }

}
