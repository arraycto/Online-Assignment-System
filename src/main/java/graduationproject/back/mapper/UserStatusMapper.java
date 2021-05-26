package graduationproject.back.mapper;

import graduationproject.back.model.entity.UserStatus;
import graduationproject.back.model.vo.GetUserInfoVo;

import java.util.List;

public interface UserStatusMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserStatus record);

    int insertSelective(UserStatus record);

    UserStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserStatus record);

    int updateByPrimaryKey(UserStatus record);

    GetUserInfoVo getUserInfo(String userId);

    int changeAccountDisable(UserStatus us);

    int deleteAccountDisable(String userId);
}