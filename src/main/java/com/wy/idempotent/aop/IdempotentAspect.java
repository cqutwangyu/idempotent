package com.wy.idempotent.aop;

import com.wy.idempotent.exception.IdempotentException;
import com.wy.idempotent.token.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * todo desc
 *
 * @author 王渔
 * @date 2022/12/8 15:25
 */
@Component
@Aspect
public class IdempotentAspect {
    @Autowired
    TokenService tokenService;

    @Pointcut("@annotation(com.wy.idempotent.annotation.AutoIdempotent)")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws IdempotentException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            tokenService.checkToken(request);
        } catch (IdempotentException e) {
            throw e;
        }
    }
}
