package com.ryan.miaosha.dao;

import com.ryan.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {
    @Select("select * from order_info where user_id = #{userId} and goods_id= #{goodsId}")
    public OrderInfo getOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date) " +
            "values(#{userId}, #{goodsId}, #{deliveryAddrId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false, resultType = long.class, statement = "select last_insert_id()")
    long addOrder(OrderInfo orderInfo);
}
