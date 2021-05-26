package graduationproject.back.exception.enums;


import graduationproject.back.exception.BaseErrorInfoInterface;

/**
 * 自定义错误描述枚举类
 * @Author
 * @Date 2020/11/7 21:01
 */
public enum CommonEnum implements BaseErrorInfoInterface {

    //测试用无意义报错,一般用于测试回滚等机制使用
    TEST_ONLY_ERROR(111, "测试用报错"),

    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    TYPES_NOT_MATCH(401,"请求的类型不符!"),


    NOT_FOUND(404, "该资源不存在!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!"),
    ACCOUNT_EXISTS_ALREADY(2005, "该账号已存在"),
    JWT_ERROR(100,"用户校验失败！请重新登录"),
    USER_NOT_EXIXT_ERROR(101,"此用户不存在"),
    USER_LOGIN_ERROR(2000, "登录失败请重试！"),
    USER_CHANGE_PWD_ERROR(2001, "账号密码错误！"),
    COURSE_NOT_EXIXT_ERROR(3000,"此课程不存在"),
    EXCEL_UPLOAD_FORMAT_ERROR(4000,"请上传.xls或者.xlsx文件"),
    FILE_SAVE_ERROR(4001,"文件保存失败"),
    DISABLE_ACCOUNT(-1003,"账户禁用，请联系管理员")
    ;

    /** 错误码 */
    private int errorCode;

    /** 错误描述 */
    private String errorMsg;

    CommonEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
