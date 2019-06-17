package com.ryan.miaosha.controller;

import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.exception.GlobalException;
import com.ryan.miaosha.result.CodeMsg;
import com.ryan.miaosha.result.Result;
import com.ryan.miaosha.service.MiaoshaUserService;
import com.ryan.miaosha.util.ValidatorUtil;
import com.ryan.miaosha.vo.LoginVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        // 登录
        miaoshaUserService.login(response, loginVo);
        return Result.success(true);
    }
}
