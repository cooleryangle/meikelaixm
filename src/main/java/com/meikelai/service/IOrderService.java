package com.meikelai.service;

import com.meikelai.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meikelai.entity.OrderDetail;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
public interface IOrderService extends IService<Order> {

    Map createOrder(Long userId, List<OrderDetail> details);
}
