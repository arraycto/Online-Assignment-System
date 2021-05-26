package graduationproject.back.exception;

/**
 * 基础的接口类，自定义的错误描述枚举类需实现该接口
 * @Author
 * @Date 2020/11/7 20:57
 */
public interface BaseErrorInfoInterface {

    public int getErrorCode();

    public String getErrorMsg();
}
