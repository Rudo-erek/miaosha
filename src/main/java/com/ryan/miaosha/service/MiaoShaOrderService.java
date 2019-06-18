package com.ryan.miaosha.service;

import com.ryan.miaosha.dao.MiaoshaOrderDao;
import com.ryan.miaosha.domain.MiaoshaOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaOrderService {

    @Autowired
    MiaoshaOrderDao miaoshaOrderDao;

    public void addMiaoshaOrder(MiaoshaOrder miaoshaOrder) {
        miaoshaOrderDao.addMiaoshaOrder(miaoshaOrder);
    }
}
