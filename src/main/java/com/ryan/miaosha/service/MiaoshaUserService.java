package com.ryan.miaosha.service;

import com.ryan.miaosha.dao.MiaoshaUserDao;
import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.result.CodeMsg;
import com.ryan.miaosha.util.MD5Util;
import com.ryan.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaUserService {
    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getUserById(long id) {
        return miaoshaUserDao.getUserById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        MiaoshaUser user = getUserById(Long.parseLong(loginVo.getMobile()));
        if (user == null) {
            return CodeMsg.MOBILE_NOTEXIST;
        }

        String inputPass = loginVo.getPassword();
        String calcPass = MD5Util.MD5(inputPass + user.getSalt());
        String realPass = user.getPassword();
        if (!calcPass.equals(realPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }

        else return CodeMsg.SUCCESS;
    }
}
