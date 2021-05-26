package graduationproject.back.service.impl;

import graduationproject.back.exception.BusinessException;
import graduationproject.back.exception.enums.CommonEnum;
import graduationproject.back.mapper.CourseMapper;
import graduationproject.back.mapper.ElectiveMapper;
import graduationproject.back.mapper.UserMapper;
import graduationproject.back.model.entity.Course;
import graduationproject.back.model.entity.Elective;
import graduationproject.back.model.entity.User;
import graduationproject.back.service.TestService;
import graduationproject.back.utils.CommonUtils;
import graduationproject.back.utils.ExcelUtil;
import org.apache.ibatis.jdbc.Null;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Pan
 * @Date 2020/12/7 23:05
 */
@Service
public class TestServiceImpl implements TestService {

    private final CourseMapper courseMapper;

    private final ElectiveMapper electiveMapper;

    private final UserMapper userMapper;

    public TestServiceImpl(UserMapper userMapper, ElectiveMapper electiveMapper, CourseMapper courseMapper) {
        this.userMapper = userMapper;
        this.electiveMapper = electiveMapper;
        this.courseMapper = courseMapper;
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


}
