package com.meikelai.controller;

import com.meikelai.entity.User;
import com.meikelai.service.ICartService;
import com.meikelai.service.ITokenService;
import com.meikelai.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Le
 * @since 2023-12-02
 */


import com.meikelai.entity.Cart;
import com.meikelai.entity.CartItem;
import com.meikelai.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private ITokenService tokenService; // 注入JWT令牌生成器
    @Autowired
    private ICartService cartService;
    @Autowired
    private IUserService userService; // 注入JWT令牌生成器
    @Value("${wechat.appSecret}")
    private String secretKey;
    private Integer getUserInfoByToken(String authorizationHeader){
        String token = authorizationHeader.substring(7); // 去掉Bearer前缀
        // 获取用户信息
        String openId = tokenService.parseToken(token);
        return  userService.selectByOpenId(openId).getUserId();
    }
    // 获取购物车
    @GetMapping("/")
    public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String authorizationHeader) {
        String userId = String.valueOf(getUserInfoByToken(authorizationHeader));
        Cart cart = cartService.getCartInfoByUserId(Integer.valueOf(userId));
        return ResponseEntity.ok(cart);
    }

    // 添加商品到购物车
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CartItem cartItem) {
        cartService.addToCart(getUserInfoByToken(authorizationHeader), cartItem);
        return ResponseEntity.ok("Item added to cart successfully");
    }

    // 更新购物车中的商品数量
    @PutMapping("/update")
    public ResponseEntity<?> updateCartItem(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CartItem cartItem) {
        cartService.updateCartItem(getUserInfoByToken(authorizationHeader), cartItem);
        return ResponseEntity.ok("Cart item updated successfully");
    }

    // 从购物车删除商品
    @DeleteMapping("/remove/{itemId}")
    public ResponseEntity<?> removeCartItem(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer itemId) {
        cartService.removeCartItem(getUserInfoByToken(authorizationHeader), itemId);
        return ResponseEntity.ok("Cart item removed successfully");
    }

    // 清空购物车
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestHeader("Authorization") String authorizationHeader) {
        cartService.clearCart(getUserInfoByToken(authorizationHeader));
        return ResponseEntity.ok("Cart cleared successfully");
    }
}

