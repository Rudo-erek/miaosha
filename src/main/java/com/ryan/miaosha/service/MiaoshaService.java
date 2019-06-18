package com.ryan.miaosha.service;

import com.ryan.miaosha.domain.MiaoshaOrder;
import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.domain.OrderInfo;
import com.ryan.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goodsVo) {
        goodsService.reduceStock(goodsVo.getId());
        OrderInfo orderInfo = orderService.addOrder(user, goodsVo);
        return orderInfo;
    }
}
