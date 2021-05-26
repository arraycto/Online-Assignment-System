package graduationproject.back.service.impl;

import graduationproject.back.exception.BusinessException;
import graduationproject.back.exception.enums.CommonEnum;
import graduationproject.back.mapper.UserMapper;
import graduationproject.back.mapper.UserStatusMapper;
import graduationproject.back.model.entity.User;
import graduationproject.back.model.entity.UserStatus;
import graduationproject.back.model.request.ChangePwdRequest;
import graduationproject.back.model.vo.GetAllStudentList;
import graduationproject.back.model.vo.GetUserInfoVo;
import graduationproject.back.model.vo.UserLoginVo;
import graduationproject.back.service.UserService;
import graduationproject.back.utils.CommonUtils;
import graduationproject.back.utils.JWTUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author Pan
 * @Date 2020/11/28 20:28
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserStatusMapper userStatusMapper;

    public UserServiceImpl(UserMapper userMapper, UserStatusMapper userStatusMapper) {
        this.userMapper = userMapper;
        this.userStatusMapper = userStatusMapper;
    }

    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     */
    @Override
    public int userRegistration(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user != null) {
            return userMapper.userRegistration(user);
        } else {
            throw new BusinessException(CommonEnum.BODY_NOT_MATCH);
        }
    }

    public User parseToUser(Map<String, String> userInfo) {
        if (userInfo.containsKey("userId") && userInfo.containsKey("pwd") && userInfo.containsKey("name") && userInfo.containsKey("accountType")) {
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setAccountType(userInfo.get("accountType"));
            user.setCreateTime(new Date());
            String userId = userInfo.get("userId");
            user.setUserId(userId.toUpperCase());
            String pwd = userInfo.get("pwd");
            user.setPwd(CommonUtils.MD5(pwd));
            return user;
        } else {
            return null;
        }
    }

    /**
     * 用户登录
     *
     * @param studentId
     * @param pwd
     * @param accountType
     * @return token
     */
    @Override
    public String userLogin(String userId, String pwd, int accountType) {
        UserLoginVo user = userMapper.userLogin(userId, CommonUtils.MD5(pwd), accountType);

        if (user != null) {
            if (user.getAccountDisabled() != null) {
                if (user.getAccountDisabled().equals("BAN")) {
                    throw new BusinessException(CommonEnum.DISABLE_ACCOUNT);
                }
            }
            User user1 = new User();
            user1.setAccountType(user.getAccountType());
            user1.setCreateTime(user.getCreateTime());
            user1.setPwd(user.getPwd());
            user1.setUserId(user.getUserId());
            user1.setName(user.getName());
            return JWTUtils.geneJsonWebToken(user1);
        } else {
            throw new BusinessException(CommonEnum.USER_LOGIN_ERROR);
        }

    }

    /**
     * 用户修改密码
     *
     * @param userId
     * @param changePwdRequest
     * @return
     */
    @Override
    public int changePwd(String userId, ChangePwdRequest changePwdRequest) {
        if (!changePwdRequest.checkJson()) {
            throw new BusinessException(CommonEnum.BODY_NOT_MATCH);
        }

        User user = userMapper.findUserByIdAndPwd(userId, CommonUtils.MD5(changePwdRequest.getOldPwd()));
        if (user != null) {
            User user1 = new User();
            user1.setUserId(userId);
            user1.setPwd(CommonUtils.MD5(changePwdRequest.getNewPwd()));
            return userMapper.changePwd(user1);
        } else {
            throw new BusinessException(CommonEnum.USER_CHANGE_PWD_ERROR);
        }
    }

    @Override
    public GetUserInfoVo getUserInfo(String userId) {
        return userStatusMapper.getUserInfo(userId);
    }

    @Override
    public List<GetAllStudentList> getAllStudentList() {
        return userMapper.getAllStudentList();
    }

    @Override
    public int changeAccountDisable(UserStatus us) {
        return userStatusMapper.changeAccountDisable(us);
    }

    @Override
    public int deleteAccountDisable(UserStatus us) {
        return userStatusMapper.deleteAccountDisable(us.getUserId());
    }

    @Override
    public List<GetAllStudentList> getAllTeacherList() {
        return userMapper.getAllTeacherList();
    }

    @Override
    public int deleteUser(String userId) {
        return userMapper.adminDeleteUser(userId);
    }

}
