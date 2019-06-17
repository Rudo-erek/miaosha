package com.ryan.miaosha.service;

import com.ryan.miaosha.dao.MiaoshaUserDao;
import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.exception.GlobalException;
import com.ryan.miaosha.redis.RedisService;
import com.ryan.miaosha.redis.keys.MiaoShaUserKeyGenerator;
import com.ryan.miaosha.result.CodeMsg;
import com.ryan.miaosha.util.MD5Util;
import com.ryan.miaosha.util.UUIDUtis;
import com.ryan.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {
    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";

    public MiaoshaUser getUserById(long id) {
        return miaoshaUserDao.getUserById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        MiaoshaUser user = getUserById(Long.parseLong(loginVo.getMobile()));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOTEXIST);
        }

        String inputPass = loginVo.getPassword();
        String calcPass = MD5Util.MD5(inputPass + user.getSalt());
        String realPass = user.getPassword();
        if (!calcPass.equals(realPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        String token = UUIDUtis.uuid();
        addCookie(response, token, user);
        return true;
    }

    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(MiaoShaUserKeyGenerator.keyByTK, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoShaUserKeyGenerator.keyByTK.getExpireTimeOut());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (token == null) {
            return null;
        }

        MiaoshaUser user = redisService.get(MiaoShaUserKeyGenerator.keyByTK, token, MiaoshaUser.class);

        // 延长有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }
}
