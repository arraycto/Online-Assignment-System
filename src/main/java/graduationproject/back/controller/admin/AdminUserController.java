package graduationproject.back.controller.admin;

import graduationproject.back.model.entity.UserStatus;
import graduationproject.back.model.request.ChangeAccountDisableRequest;
import graduationproject.back.model.vo.GetAllStudentList;
import graduationproject.back.service.UserService;
import graduationproject.back.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Pan
 * @Date 2021/5/9 17:49
 */
@RestController
@RequestMapping("api/admin/user")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService ) {
        this.userService = userService;

    }

    /**
     * 获取学生信息并根据查询到的不同的状态值设置权限
     * （就是前端会有三个按钮，分别为账户禁用、课程禁用、作业禁用，但是数据库中用一个字段来存储了这些值，需要在这里分类出来）
     * @return
     */
    @GetMapping("getAllStudentList")
    public JsonData getAllStudentList() {

        List<GetAllStudentList> list = userService.getAllStudentList();

        List<GetAllStudentList> list2 = new ArrayList<>();
        for (GetAllStudentList l : list){
            if (l.getAccountDisabled() == null ||l.getAccountDisabled().equals("")) {  //正常访问
                l.setAccountDisabled("1");
                l.setCourseDisabled("1");
                l.setAssignmentDisabled("1");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("0-1")){//禁止访问课程
                l.setAccountDisabled("1");
                l.setCourseDisabled("0");
                l.setAssignmentDisabled("1");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("0-2")){//禁止访问作业
                l.setAccountDisabled("1");
                l.setCourseDisabled("1");
                l.setAssignmentDisabled("0");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("0-3")){//禁止访问课程与作业
                l.setAccountDisabled("1");
                l.setCourseDisabled("0");
                l.setAssignmentDisabled("0");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("BAN")){//账户禁用
                l.setAccountDisabled("0");
                l.setCourseDisabled("1");
                l.setAssignmentDisabled("1");
                list2.add(l);
            }
        }

        return list2 != null ? JsonData.buildSuccess(list2,"学生用户获取成功"):JsonData.buildError("获取失败");
    }

    /**
     * 获取教师信息并根据查询到的不同的状态值设置权限
     * （就是前端会有三个按钮，分别为账户禁用、课程禁用、作业禁用，但是数据库中用一个字段来存储了这些值，需要在这里分类出来）
     * @return
     */
    @GetMapping("getAllTeacherList")
    public JsonData getAllTeacherList() {

        List<GetAllStudentList> list = userService.getAllTeacherList();

        List<GetAllStudentList> list2 = new ArrayList<>();
        for (GetAllStudentList l : list){
            if (l.getAccountDisabled() == null ||l.getAccountDisabled().equals("")) {//正常访问
                l.setAccountDisabled("1");
                l.setCourseDisabled("1");
                l.setAssignmentDisabled("1");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("1-1")){//禁止访问课程
                l.setAccountDisabled("1");
                l.setCourseDisabled("0");
                l.setAssignmentDisabled("1");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("1-2")){//禁止访问作业
                l.setAccountDisabled("1");
                l.setCourseDisabled("1");
                l.setAssignmentDisabled("0");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("1-3")){//禁止访问课程与作业
                l.setAccountDisabled("1");
                l.setCourseDisabled("0");
                l.setAssignmentDisabled("0");
                list2.add(l);
            }else if (l.getAccountDisabled().equals("BAN")){//账户禁用
                l.setAccountDisabled("0");
                l.setCourseDisabled("1");
                l.setAssignmentDisabled("1");
                list2.add(l);
            }
        }

        return list2 != null ? JsonData.buildSuccess(list2,"学生用户获取成功"):JsonData.buildError("获取失败");
    }

    /**
     * 更改账户状态
     * @param r
     * @return
     */
    @PostMapping("changeAccountDisable")
    public JsonData getAllTeacher(@RequestBody ChangeAccountDisableRequest r){

        UserStatus us = new UserStatus();
        us.setAccountType(r.getAccountType());
        us.setUserId(r.getUserId());
        us.setName(r.getName());
        if(r.getAccountDisabled().equals("0")){
            us.setAccountDisabled("BAN");
        } else if (r.getAccountType().equals("0")){
            if(r.getCourseDisabled().equals("0")&&r.getAssignmentDisabled().equals("1")){
                us.setAccountDisabled("0-1");
            }else if(r.getCourseDisabled().equals("1")&&r.getAssignmentDisabled().equals("0")){
                us.setAccountDisabled("0-2");
            }else if(r.getCourseDisabled().equals("0")&&r.getAssignmentDisabled().equals("0")){
                us.setAccountDisabled("0-3");
            }else{
                int result = userService.deleteAccountDisable(us);
                return result !=0 ?JsonData.buildSuccess("修改成功"):JsonData.buildError("修改失败");
            }
        }else if (r.getAccountType().equals("1")){
            if(r.getCourseDisabled().equals("0")&&r.getAssignmentDisabled().equals("1")){
                us.setAccountDisabled("1-1");
            }else if(r.getCourseDisabled().equals("1")&&r.getAssignmentDisabled().equals("0")){
                us.setAccountDisabled("1-2");
            }else if(r.getCourseDisabled().equals("0")&&r.getAssignmentDisabled().equals("0")){
                us.setAccountDisabled("1-3");
            }else{
                int result = userService.deleteAccountDisable(us);
                return result !=0 ?JsonData.buildSuccess("修改成功"):JsonData.buildError("修改失败");
            }
        }

        int result = userService.changeAccountDisable(us);

        return result !=0 ?JsonData.buildSuccess("修改成功"):JsonData.buildError("修改失败");
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PostMapping("deleteUser")
    public JsonData deleteUser(@RequestParam(value = "userId",required = true)String userId){
        int result = userService.deleteUser(userId);
        return result != 0 ? JsonData.buildSuccess("删除成功") : JsonData.buildError("删除失败");
    }
}
