package graduationproject.back.service.impl;

import graduationproject.back.exception.BusinessException;
import graduationproject.back.exception.enums.CommonEnum;
import graduationproject.back.mapper.CourseMapper;
import graduationproject.back.mapper.ElectiveMapper;
import graduationproject.back.mapper.UserMapper;
import graduationproject.back.model.entity.Course;
import graduationproject.back.model.entity.Elective;
import graduationproject.back.model.entity.User;
import graduationproject.back.model.request.AdminCreateCourseRequest;
import graduationproject.back.model.request.CreateCourseRequest;
import graduationproject.back.model.request.ModifyCourseRequest;
import graduationproject.back.model.vo.*;
import graduationproject.back.service.CourseService;
import graduationproject.back.utils.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/1 16:25
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final ElectiveMapper electiveMapper;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;

    public CourseServiceImpl(CourseMapper courseMapper, ElectiveMapper electiveMapper, UserMapper userMapper) {
        this.courseMapper = courseMapper;
        this.electiveMapper = electiveMapper;
        this.userMapper = userMapper;
    }

    /**
     * 创建课程
     * @param userId
     * @param createCourseRequest
     * @return
     */
    @Override
    public int createCourse(String userId, CreateCourseRequest createCourseRequest) {
        if(!createCourseRequest.checkJson())
        {
            throw new BusinessException(CommonEnum.BODY_NOT_MATCH);
        }
        Course course = new Course();
        course.setAuthorId(userId);
        course.setCourseDesc(createCourseRequest.getCourseDesc());
        course.setCourseName(createCourseRequest.getCourseName());
        course.setCreateTime(new Date());
        return courseMapper.createCourse(course);
    }

    @Override
    public int modifyCourse(String userId, ModifyCourseRequest modifyCourseRequest) {
        if(!modifyCourseRequest.checkJson())
        {
            throw new BusinessException(CommonEnum.BODY_NOT_MATCH);
        }
        Course course = new Course();
        course.setCourseId(modifyCourseRequest.getCourseId());
        course.setAuthorId(userId);
        course.setCourseDesc(modifyCourseRequest.getCourseDesc());
        course.setCourseName(modifyCourseRequest.getCourseName());

        return courseMapper.modifyCourse(course);

    }

    @Override
    public int deleteCourse(String userId, Integer courseId) {

        return courseMapper.deleteCourse(userId,courseId);
    }

    @Override
    public List<CourseVo> getAllCourse(String userId) {

        return courseMapper.getAllCourse(userId);
    }

    @Override
    public int joinCourse(String userId, Integer courseId) {

        return electiveMapper.joinCourse(userId,courseId);
    }

    @Transactional
    @Override
    public boolean batchImportExcel(String fileName, MultipartFile file ,Integer courseId) {

        Course course = courseMapper.selectByCourseId(courseId);
        if(course == null)
        {
            System.out.println(123);
            throw new BusinessException(CommonEnum.COURSE_NOT_EXIXT_ERROR);
        }


        ExcelUtil excelUtil = new ExcelUtil();

        List<User> userList = null;
        try {
            userList = excelUtil.getExcelData(fileName,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userList == null){
            return false;
        }
        for (User userResord : userList) {
            String userId = userResord.getUserId();
            User user = userMapper.selectByUserId(userId);
            if (user == null) {
                userMapper.addUserByExcelBatchImport(userResord);
            }
            Elective elective = electiveMapper.selectByUserIdAndCourseId(userId,courseId);
            if(elective == null){
                electiveMapper.addElectiveByExcelBatchImport(userId,courseId);
            }
        }

        return true;
    }

    @Override
    public List<CourseStudentInfoVo> getAllStudentInfo(Integer courseId) {
        return courseMapper.getAllStudentInfo(courseId);
    }

    @Override
    public List<Course> teacherGetAllCourse(String userId) {
        return courseMapper.teacherGetAllCourse(userId);
    }

    @Override
    public List<GetAllMyCourseVo> getAllMyCourse(String userId) {
        return courseMapper.getAllMyCourse(userId);
    }

    @Override
    public List<AdminGetAllCourseVo> adminGetAllCourse() {
        return courseMapper.adminGetAllCourse();
    }

    @Override
    public List<AdminGetTeacherList> getTeacherList() {
        return courseMapper.getTeacherList();
    }

    @Override
    public int adminCreateCourse(String authorId, AdminCreateCourseRequest adminCreateCourseRequest) {
        Course course = new Course();
        course.setAuthorId(adminCreateCourseRequest.getAuthorId());
        course.setCourseDesc(adminCreateCourseRequest.getCourseDesc());
        course.setCourseName(adminCreateCourseRequest.getCourseName());
        course.setCreateTime(new Date());
        return courseMapper.createCourse(course);
    }

    @Override
    public int deleteStudentOnCourse(String userId, Integer courseId) {
        return courseMapper.deleteStudentOnCourse(userId,courseId);
    }

}
