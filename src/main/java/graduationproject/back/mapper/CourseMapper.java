package graduationproject.back.mapper;

import graduationproject.back.model.entity.Course;
import graduationproject.back.model.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    int createCourse(Course course);

    int modifyCourse(Course course);

    int deleteCourse(@Param("authorId") String userId, @Param("courseId") Integer courseId);

    List<CourseVo> getAllCourse(String userId);

    Course selectByCourseId(Integer courseId);

    List<CourseStudentInfoVo> getAllStudentInfo(Integer courseId);

    List<Course> teacherGetAllCourse(String userId);

    List<GetAllMyCourseVo> getAllMyCourse(String userId);

    List<AdminGetAllCourseVo> adminGetAllCourse();

    List<AdminGetTeacherList> getTeacherList();

    int deleteStudentOnCourse(String userId, Integer courseId);
}