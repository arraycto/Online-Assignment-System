package graduationproject.back.utils;


import graduationproject.back.exception.BaseErrorInfoInterface;

/**
 * @Author Pan
 * @Date 2020/11/6 12:09
 */
public class JsonData {

    /**
     * code: 0表示成功，1表示处理中，-1表示失败
     */
    private Integer code;

    /**
     * 业务数据
     */
    private Object data;

    /**
     * 返回信息表示
     */
    private String msg;

    public JsonData() {
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功且不用返回数据
     * @return
     */
    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }

    /**
     * 成功，返回数据
     * @param data
     * @return
     */
    public static JsonData buildSuccess(Object data){
        return new JsonData(0,data,null);
    }

    /**
     * 成功，返回msg信息
     * @param msg
     * @return
     */
    public static JsonData buildSuccess(String msg){
        return new JsonData(0,msg);
    }

    /**
     * 成功，返回data数据与msg提示信息
     * @param data
     * @param msg
     * @return
     */
    public static JsonData buildSuccess(Object data,String msg){
        return new JsonData(0,data,msg);
    }

    /**
     *失败，固定状态码
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg){
        return new JsonData(-1,null,msg);
    }

    /**
     *失败，自定义状态码
     * @param code
     * @param msg
     * @return
     */
    public static JsonData buildError(Integer code,String msg){
        return new JsonData(code,null,msg);
    }

    /**
     *返回自定义异常的数据
     * @param errorInfo
     * @return
     */
    public static JsonData customException(BaseErrorInfoInterface errorInfo) {
        return new JsonData(errorInfo.getErrorCode(),null,errorInfo.getErrorMsg());
    }

    /**
     * 返回自定义异常的数据
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static JsonData customException(Integer errorCode, String errorMsg) {
        return new JsonData(errorCode,null,errorMsg);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
