package graduationproject.back.service;

import graduationproject.back.model.entity.UserStatus;
import graduationproject.back.model.request.ChangePwdRequest;
import graduationproject.back.model.vo.GetAllStudentList;
import graduationproject.back.model.vo.GetUserInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @Author Pan
 * @Date 2020/11/28 20:27
 */
public interface UserService {

    int userRegistration(Map<String, String> userInfo);

    String userLogin(String studentId, String pwd, int accountType);

    int changePwd(String userId, ChangePwdRequest changePwdRequest);

    GetUserInfoVo getUserInfo(String userId);

    List<GetAllStudentList> getAllStudentList();

    int changeAccountDisable(UserStatus us);

    int deleteAccountDisable(UserStatus us);

    List<GetAllStudentList> getAllTeacherList();

    int deleteUser(String userId);
}
