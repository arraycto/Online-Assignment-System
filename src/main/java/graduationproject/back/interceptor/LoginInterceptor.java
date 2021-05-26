package graduationproject.back.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import graduationproject.back.utils.JWTUtils;
import graduationproject.back.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器类，在进入到controller之前拦截方法
 * @Author Pan
 * @Date 2020/11/8 18:46
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accessToken = request.getHeader("token");
            if(accessToken == null)
            {
                accessToken = request.getParameter("token");
            }

            if(StringUtils.isNotBlank(accessToken))
            {
                Claims claims = JWTUtils.checkJWT(accessToken);
                if (claims == null)
                {
                    //告诉登录过期，重新登录
                    sendJsonMessage(response, JsonData.buildError(-1001,"登录过期，重新登录"));
                    return false;
                }
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                String userId = (String) claims.get("userId");
                String accountType = (String) claims.get("accountType");

                request.setAttribute("id", id);
                request.setAttribute("name", name);
                request.setAttribute("userId", userId);
                request.setAttribute("accountType", accountType);

                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        sendJsonMessage(response, JsonData.buildError(-1002,"登录过期，重新登录"));
        return false;
    }
    /**
     * 响应json数据给前端
     * @param response
     * @param obj
     */
    private void sendJsonMessage(HttpServletResponse response, Object obj) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
