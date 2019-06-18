package com.ryan.miaosha.controller;

import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.service.GoodsService;
import com.ryan.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/to_detail/{goodsId}")
    public String goodsDetail(@PathVariable("goodsId") long goodsId, Model model, MiaoshaUser user) {
        model.addAttribute("user", user);

        GoodsVo goodsVo = goodsService.getGoodsByGoodsId(goodsId);

        model.addAttribute("goods", goodsVo);

        int miaoshaStatus = 0;
        int remainSecondes = 0;

        long startDate = goodsVo.getStartDate().getTime();
        long endDate = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();

        if (now < startDate) {
            remainSecondes = (int) (startDate - now) / 1000;
            miaoshaStatus = 0;
        } else if (now > endDate) {
            remainSecondes = -1;
            miaoshaStatus = 2;
        } else {
            remainSecondes = 0;
            miaoshaStatus = 1;
        }
        model.addAttribute("miaoshaStatus" , miaoshaStatus);
        model.addAttribute("remainSeconds", remainSecondes);

        return "goods_detail";
    }
}
