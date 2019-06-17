package com.ryan.miaosha.controller;

import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.service.GoodsService;
import com.ryan.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toList(Model model, MiaoshaUser user) {
        if (user == null)
            return "login";
        model.addAttribute("user", user);

        List<GoodsVo> listGoods = goodsService.listGoodsVo();
        model.addAttribute("listGoods", listGoods);
        return "goods_list";
    }
}
