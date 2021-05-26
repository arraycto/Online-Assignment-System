package graduationproject.back.exception.utils;


import graduationproject.back.exception.BaseErrorInfoInterface;

/**
 * 返回数据类
 * @Author
 * @Date 2020/11/7 22:28
 */
public class ResponseJsonData {

    private Integer code;

    /**
     * 返回信息表示
     */
    private String msg;

    public ResponseJsonData() {
    }

    public ResponseJsonData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     *返回自定义异常的数据
     * @param errorInfo
     * @return
     */
    public static ResponseJsonData customException(BaseErrorInfoInterface errorInfo) {
        return new ResponseJsonData(errorInfo.getErrorCode(),errorInfo.getErrorMsg());
    }

    /**
     * 返回自定义异常的数据
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static ResponseJsonData customException(Integer errorCode, String errorMsg) {
        return new ResponseJsonData(errorCode,errorMsg);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
