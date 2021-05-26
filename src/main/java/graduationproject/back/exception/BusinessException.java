package graduationproject.back.exception;

/**
 * 自定义业务异常处理类，用于处理发生的业务异常
 * @Author
 * @Date 2020/11/7 21:12
 */
public class BusinessException extends RuntimeException {

    private Integer errorCode;

    private String errorMsg;

    public BusinessException() {
    }

    public BusinessException(BaseErrorInfoInterface baseErrorInfoInterface) {
        super(String.valueOf(baseErrorInfoInterface.getErrorCode()));
        this.errorCode = baseErrorInfoInterface.getErrorCode();
        this.errorMsg = baseErrorInfoInterface.getErrorMsg();
    }

    public BusinessException(BaseErrorInfoInterface baseErrorInfoInterface, Throwable cause) {
        super(String.valueOf(baseErrorInfoInterface.getErrorCode()), cause);
        this.errorCode = baseErrorInfoInterface.getErrorCode();
        this.errorMsg = baseErrorInfoInterface.getErrorMsg();
    }

    public BusinessException(Throwable cause, Integer errorCode, String errorMsg) {
        super(String.valueOf(errorCode),cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
