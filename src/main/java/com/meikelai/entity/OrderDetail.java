package com.meikelai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "orderDetailId", type = IdType.AUTO)
    private Integer orderDetailId;

    private Integer orderId;

    private Integer dishId;

    private Integer quantity;

    private BigDecimal price;


    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
        "orderDetailId=" + orderDetailId +
        ", orderId=" + orderId +
        ", dishId=" + dishId +
        ", quantity=" + quantity +
        ", price=" + price +
        "}";
    }
}
