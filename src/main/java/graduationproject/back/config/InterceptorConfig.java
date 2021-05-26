package graduationproject.back.config;

import graduationproject.back.interceptor.CorsInterceptor;
import graduationproject.back.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Pan
 * @Date 2020/11/8 18:55
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截全部路径，这个跨域需要放在最上面
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
        /**
         * 用于登录的拦截
         * 不拦截哪些路径，斜杠/一定要加
         */
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/*/*/**")
                .excludePathPatterns("/api/user/login","/api/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
