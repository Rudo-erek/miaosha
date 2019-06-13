package com.ryan.miaosha.controller;

import com.ryan.miaosha.result.CodeMsg;
import com.ryan.miaosha.result.Result;
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
}
