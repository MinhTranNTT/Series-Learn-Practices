package org.crocodile.apptour.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public MyInterceptor myInterceptor() {
        log.error("Đăng ký Interceptor - myInterceptor");
        return new MyInterceptor();
    }

    @Bean
    public RequestTimeInterceptor requestTimeInterceptor() {
        log.error("Đăng ký Req time Interceptor - requestTimeInterceptor");
        return new RequestTimeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor())
        // registry.addInterceptor(requestTimeInterceptor())
                .addPathPatterns("/demo/*")
                .excludePathPatterns("/demo/info");
    }

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("*") //浏览器允许所有的域访问 / 注意 * 不能满足带有cookie的访问,Origin 必须是全匹配
    //             .allowedHeaders("*")
    //             .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
    //             .allowCredentials(true) // 允许带cookie访问
    //             .maxAge(3600);
    // }

}
