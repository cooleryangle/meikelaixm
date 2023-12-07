package com.meikelai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang Le
 * @since 2023-12-02
 */
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cartId", type = IdType.AUTO)
    private Integer cartId;

    private Integer userId;

    private List<CartItem> cartItems;


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Cart{" +
        "cartId=" + cartId +
        ", userId=" + userId +
        "}";
    }
}
