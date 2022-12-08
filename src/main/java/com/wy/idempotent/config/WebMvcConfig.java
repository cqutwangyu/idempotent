package com.wy.idempotent.config;

import com.wy.idempotent.interceptor.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * todo desc
 *
 * @author 王渔
 * @date 2022/12/8 15:40
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    RepeatSubmitInterceptor repeatSubmitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }
}
