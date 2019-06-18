package com.ryan.miaosha.dao;

import com.ryan.miaosha.domain.MiaoshaOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MiaoshaOrderDao {

    @Insert("insert miaosha_order(user_id, order_id, goods_id) values(#{userId}, #{orderId}, #{goodsId})")
    void addMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
