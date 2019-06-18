package com.ryan.miaosha.service;

import com.ryan.miaosha.dao.OrderDao;
import com.ryan.miaosha.domain.MiaoshaOrder;
import com.ryan.miaosha.domain.MiaoshaUser;
import com.ryan.miaosha.domain.OrderInfo;
import com.ryan.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    MiaoShaOrderService miaoshaOrderService;

    public OrderInfo getOrderByUserIdGoodsId(long mobile, long goodsId) {
        return orderDao.getOrderByUserIdGoodsId(mobile, goodsId);
    }

    @Transactional
    public OrderInfo addOrder(MiaoshaUser user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());
        long id = orderDao.addOrder(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrderService.addMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
