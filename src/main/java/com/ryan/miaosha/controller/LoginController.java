package com.ryan.miaosha.controller;

import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.result.CodeMsg;
import com.ryan.miaosha.result.Result;
import com.ryan.miaosha.service.MiaoshaUserService;
import com.ryan.miaosha.util.ValidatorUtil;
import com.ryan.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (StringUtils.isEmpty(password)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }

        CodeMsg cm = miaoshaUserService.login(loginVo);
        if (cm.getCode() != 0) {
            return Result.error(cm);
        }
        return Result.success(true);
    }
}
