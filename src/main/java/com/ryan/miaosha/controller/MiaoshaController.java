package com.ryan.miaosha.controller;

import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.domain.OrderInfo;
import com.ryan.miaosha.result.CodeMsg;
import com.ryan.miaosha.service.GoodsService;
import com.ryan.miaosha.service.MiaoshaService;
import com.ryan.miaosha.service.OrderService;
import com.ryan.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String doMiaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        // 判断用户是否登录
        if (user == null) {
            return "login";
        }

        // 判断是否已经抢到
        OrderInfo orderInfo = orderService.getOrderByUserIdGoodsId(user.getId(), goodsId);
        if (orderInfo != null) {
            model.addAttribute("errMsg", CodeMsg.MIAOSHA_ERROR);
            return "miaosha_fail";
        }

        // 检查库存余量
        GoodsVo goodsVo = goodsService.getGoodsByGoodsId(goodsId);
        if (goodsVo.getStockCount() <= 0) {
            model.addAttribute("errMsg", CodeMsg.STOCK_ERROR);
            return "miaosh_fail";
        }

        // 下订单、减库存、写入订单
        orderInfo = miaoshaService.miaosha(user, goodsVo);
        model.addAttribute("orderId", orderInfo.getId());
        return "success";
    }
}
