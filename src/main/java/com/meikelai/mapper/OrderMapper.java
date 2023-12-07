package com.meikelai.mapper;

import com.meikelai.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meikelai.entity.OrderDetail;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */

public interface OrderMapper extends BaseMapper<Order> {

    void insertOrder(Order order);

    void insertOrderDetails(Order order, List<OrderDetail> details);
}
