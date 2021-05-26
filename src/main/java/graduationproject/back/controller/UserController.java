package graduationproject.back.controller;

import graduationproject.back.model.request.ChangePwdRequest;
import graduationproject.back.model.request.LoginRequest;
import graduationproject.back.model.vo.GetUserInfoVo;
import graduationproject.back.service.UserService;
import graduationproject.back.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Pan
 * @Date 2020/11/28 19:38
 */

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册（用在管理员创建用户）
     * @param userInfo
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String, String> userInfo) {
        int result = userService.userRegistration(userInfo);
        return result == 1 ? JsonData.buildSuccess("注册成功") : JsonData.buildError("注册失败");
    }

    /**
     * 登录
     * @param login
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest login) {

        String token = userService.userLogin(login.getStudentId().toUpperCase(), login.getPwd(), login.getAccountType());

        return token != null ? JsonData.buildSuccess(token, "登录成功！") : JsonData.buildError("账号密码错误！");
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("logout")
    public JsonData logout() {

        return JsonData.buildSuccess("退出成功！");
    }

    /**
     * 修改密码
     * @param request
     * @param changePwdRequest
     * @return
     */
    @PostMapping("changePwd")
    public JsonData changePwd(HttpServletRequest request, @RequestBody ChangePwdRequest changePwdRequest) {

        String userId = (String) request.getAttribute("userId");

        int result = userService.changePwd(userId, changePwdRequest);
        return result == 1 ? JsonData.buildSuccess("修改密码成功") : JsonData.buildError("修改失败");

    }

    /**
     * 获得用户信息
     * @param request
     * @return
     */
    @GetMapping("info")
    public JsonData info(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");

        GetUserInfoVo infoList = userService.getUserInfo(userId);

        Map<String, Object> map = new HashMap<>();

        String name = infoList.getName();
        String accountType = infoList.getAccountType();
        String createTime = infoList.getCreateTime();
        String accountDisabled = infoList.getAccountDisabled();
        String[] accountType1 = new String[]{accountType};
        String[] accountDisabled1 = new String[]{accountDisabled};
        map.put("name", name);
        map.put("userId", userId);
        map.put("accountType", accountType1);
        map.put("createTime", createTime);
        if (accountDisabled != null) {
            if (!accountDisabled.equals("")) {
                map.put("accountDisabled", accountDisabled1);
            }
        }
        return JsonData.buildSuccess(map);
    }
}
