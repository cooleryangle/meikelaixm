package com.meikelai.service.impl;

import com.meikelai.entity.Cart;
import com.meikelai.entity.CartItem;
import com.meikelai.mapper.CartItemMapper;
import com.meikelai.mapper.CartMapper;
import com.meikelai.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Le
 * @since 2023-12-02
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemMapper cartItemMapper;


    // 获取购物车
    public Cart getCartByUserId(Integer userId) {
        return cartMapper.selectCartByUserId(userId);
    }

    public Cart getCartInfoByUserId(Integer userId) {
        return cartMapper.selectCartInfoByUserId(userId);
    }


    // 添加商品到购物车
    @Transactional
    public void addToCart(Integer userId, CartItem cartItem) {
        Cart cart = cartMapper.selectCartByUserId(userId);
        if (cart == null) {
            // 如果购物车不存在，则创建一个新的购物车
            cart = new Cart();
            cart.setUserId(userId);
            cartMapper.insert(cart);
        }

        cartItem.setCartId(cart.getCartId());
        cartItemMapper.insert(cartItem);
    }

    // 更新购物车中的商品数量
    @Transactional
    public void updateCartItem(Integer userId, CartItem updatedItem) {
        Cart cart = cartMapper.selectCartByUserId(userId);
        if (cart != null) {
            CartItem existingItem = cartItemMapper.selectById(updatedItem.getCartItemId());
            if (existingItem != null && existingItem.getCartId().equals(cart.getCartId())) {
                existingItem.setQuantity(updatedItem.getQuantity());
                cartItemMapper.updateById(existingItem);
            }
        }
    }

    // 从购物车删除商品
    @Transactional
    public void removeCartItem(Integer userId, Integer itemId) {
        Cart cart = cartMapper.selectCartByUserId(userId);
        if (cart != null) {
            cartItemMapper.deleteById(itemId);
        }
    }

    // 清空购物车
    @Transactional
    public void clearCart(Integer userId) {
        Cart cart = cartMapper.selectCartByUserId(userId);
        if (cart != null) {
            cartItemMapper.deleteByCartId(cart.getCartId());
        }
    }
}
