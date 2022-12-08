package com.wy.idempotent.token;

import com.wy.idempotent.exception.IdempotentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * todo desc
 *
 * @author 王渔
 * @date 2022/12/8 14:40
 */
@Component
public class TokenService {
    @Autowired
    RedisService redisService;

    public String createToken() {
        String token = UUID.randomUUID().toString();
        redisService.setEx(token, token, 10000L);
        return token;
    }

    public boolean checkToken(HttpServletRequest req) throws IdempotentException {
        String token = req.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = req.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                throw new IdempotentException("token 不存在");
            }
        }
        if (!redisService.exist(token)) {
            throw new IdempotentException("重复的操作");
        }
        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new IdempotentException("重复的操作");
        }
        return true;
    }

}
