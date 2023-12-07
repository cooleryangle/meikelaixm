package com.meikelai.service;

import com.meikelai.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meikelai.entity.CartItem;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Le
 * @since 2023-12-02
 */
public interface ICartService extends IService<Cart> {

    Cart getCartByUserId(Integer userId);

    void addToCart(Integer userId, CartItem cartItem);

    void updateCartItem(Integer userId, CartItem cartItem);

    void removeCartItem(Integer userId, Integer itemId);

    void clearCart(Integer userId);

    Cart getCartInfoByUserId(Integer integer);
}
