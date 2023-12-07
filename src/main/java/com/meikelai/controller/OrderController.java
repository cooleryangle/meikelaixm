package com.meikelai.controller;

import com.meikelai.entity.CheckoutRequest;
import com.meikelai.entity.Order;
import com.meikelai.entity.OrderDetail;
import com.meikelai.service.IOrderService;
import com.meikelai.service.ITokenService;
import com.meikelai.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private ITokenService tokenService; // 注入JWT令牌生成器

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
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CheckoutRequest checkoutRequest) {
        try {
            // 这里你需要从请求中获取userId和订单详情列表
            Long userId = Long.valueOf(getUserInfoByToken(authorizationHeader));
            List<OrderDetail> details = checkoutRequest.getDetails();

            // 创建订单
            Map order = orderService.createOrder(userId, details);

            // 返回订单信息
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            // 如果出现异常，返回错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
