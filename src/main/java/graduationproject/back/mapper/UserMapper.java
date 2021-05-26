package graduationproject.back.mapper;

import graduationproject.back.model.entity.User;
import graduationproject.back.model.vo.GetAllStudentList;
import graduationproject.back.model.vo.UserLoginVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 用户注册
     * @param user
     * @return
     */
    int userRegistration(User user);

    /**
     * 用户登录
     * @param userId
     * @param pwd
     * @param accountType
     * @return
     */
    UserLoginVo userLogin(@Param("userId") String userId, @Param("pwd") String pwd, @Param("accountType") int accountType);

    /**
     * 根据ID和密码来查找用户是否存在
     * @param userId
     * @param oldPwd
     * @return
     */
    User findUserByIdAndPwd(@Param("userId") String userId, @Param("pwd") String oldPwd);

    int changePwd(User user);


    /**
     * 根据excel表格的数据导入用户
     * @param user
     */
    void addUserByExcelBatchImport(User user);

    /**
     * 根据UserID搜索当前用户是否存在
     * @param userId
     * @return
     */
    User selectByUserId(@Param("userId") String userId);

    List<GetAllStudentList> getAllStudentList();

    List<GetAllStudentList> getAllTeacherList();

    int adminDeleteUser(String userId);
}