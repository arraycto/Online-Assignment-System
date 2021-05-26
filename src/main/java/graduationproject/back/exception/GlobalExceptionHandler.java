package graduationproject.back.exception;


import graduationproject.back.exception.enums.CommonEnum;
import graduationproject.back.exception.utils.ResponseJsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;


import javax.servlet.http.HttpServletRequest;

/**
 * 自定义全局异常处理
 * @Author
 * @Date 2020/11/7 20:56
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseJsonData businessExceptionHandler(HttpServletRequest req, BusinessException e){
        logger.error("[ 业务异常 ]：{}",e.getErrorMsg());
        return ResponseJsonData.customException(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResponseJsonData exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("[ 空指针异常 ]：{}",e.toString());
        return ResponseJsonData.customException(CommonEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理账户不存在异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public ResponseJsonData exceptionHandler(HttpServletRequest req, DuplicateKeyException e){
        logger.error("[ 用户账户异常 ]：{}",e.getMessage());
        return ResponseJsonData.customException(CommonEnum.ACCOUNT_EXISTS_ALREADY);
    }

    /**
     * 处理404异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public ResponseJsonData exceptionHandler(HttpServletRequest req, NoHandlerFoundException e){
        logger.error("[ 请求异常 ]{}",e.toString());
        return ResponseJsonData.customException(CommonEnum.NOT_FOUND);
    }

    /**
     * 处理请求数据异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = org.springframework.http.converter.HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseJsonData exceptionHandler(HttpServletRequest req, org.springframework.http.converter.HttpMessageNotReadableException e){
        logger.error("[ 请求数据异常 ]{}",e.toString());
        return ResponseJsonData.customException(CommonEnum.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = org.springframework.web.bind.MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseJsonData exceptionHandler(HttpServletRequest req, org.springframework.web.bind.MissingServletRequestParameterException e){
        logger.error("[ 请求数据异常 ]{}",e.toString());
        return ResponseJsonData.customException(CommonEnum.BODY_NOT_MATCH);
    }
    /**
     * 处理请求类型异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = org.springframework.web.HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseJsonData exceptionHandler(HttpServletRequest req, org.springframework.web.HttpRequestMethodNotSupportedException e){
        logger.error("[ 请求类型异常 ]{}",e.toString());
        return ResponseJsonData.customException(CommonEnum.TYPES_NOT_MATCH);
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResponseJsonData exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("[ 系统异常 ]{}",e.toString());
        return ResponseJsonData.customException(CommonEnum.INTERNAL_SERVER_ERROR);
    }

}