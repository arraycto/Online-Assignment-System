package graduationproject.back.service;

import graduationproject.back.model.entity.Course;
import graduationproject.back.model.request.AdminCreateCourseRequest;
import graduationproject.back.model.request.CreateCourseRequest;
import graduationproject.back.model.request.ModifyCourseRequest;
import graduationproject.back.model.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/1 16:24
 */
public interface CourseService {

    int createCourse(String userId, CreateCourseRequest createCourseRequest);

    int modifyCourse(String userId, ModifyCourseRequest modifyCourseRequest);

    int deleteCourse(String userId, Integer courseId);

    List<CourseVo> getAllCourse(String userId);

    int joinCourse(String userId, Integer courseId);

    boolean batchImportExcel(String fileName, MultipartFile file, Integer courseId);

    List<CourseStudentInfoVo> getAllStudentInfo(Integer courseId);

    List<Course> teacherGetAllCourse(String userId);

    List<GetAllMyCourseVo> getAllMyCourse(String userId);

    List<AdminGetAllCourseVo> adminGetAllCourse();

    List<AdminGetTeacherList> getTeacherList();

    int adminCreateCourse(String authorId, AdminCreateCourseRequest adminCreateCourseRequest);

    int deleteStudentOnCourse(String userId, Integer courseId);
}
