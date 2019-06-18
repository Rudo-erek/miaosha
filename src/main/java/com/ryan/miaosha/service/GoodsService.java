package com.ryan.miaosha.service;

import com.ryan.miaosha.dao.GoodsDao;
import com.ryan.miaosha.domain.MiaoshaGoods;
import com.ryan.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsByGoodsId(long goodsId) {
        return goodsDao.getGoodsByGoodsId(goodsId);
    }

    @Transactional
    public void reduceStock(long goodsId) {
        goodsDao.reduceStock(goodsId);
    }
}
