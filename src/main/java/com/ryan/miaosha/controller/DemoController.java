package com.ryan.miaosha.controller;

import com.ryan.miaosha.domain.User;
import com.ryan.miaosha.redis.RedisService;
import com.ryan.miaosha.redis.keys.UserKeyGenerator;
import com.ryan.miaosha.result.CodeMsg;
import com.ryan.miaosha.result.Result;
import com.ryan.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "hello world!";
    }


    @RequestMapping("/hello")
    @ResponseBody
    public Result hello() {
        return Result.success("Hello, Ryan!");
    }

    @RequestMapping("/helloerr")
    @ResponseBody
    public Result hellerr() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Ryan");
        return "hello";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Result<User> getUser() {
        User user = userService.getUser(1);
        return Result.success(user);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public boolean insertUser() {
        User[] users = new User[2];
        users[0] = new User();
        users[0].setId(2);
        users[0].setName("222");

        users[1] = new User();
        users[1].setName("333");
        users[1].setId(1);

        userService.insertTransaction(users);
        return true;
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redis() {
        User user = new User();
        user.setId(1);
        user.setName("Ryan");
        redisService.set(UserKeyGenerator.userKeyById, "1", user);
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisget() {
        User user = redisService.get(UserKeyGenerator.userKeyById, "1", User.class);
        return Result.success(user);
    }

}
