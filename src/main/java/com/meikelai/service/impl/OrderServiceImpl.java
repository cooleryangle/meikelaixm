package com.meikelai.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.meikelai.config.MKLConfig;
import com.meikelai.entity.Order;
import com.meikelai.entity.OrderDetail;
import com.meikelai.mapper.OrderMapper;
import com.meikelai.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meikelai.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public Map createOrder(Long userId, List<OrderDetail> details) {
        // 计算订单总金额
        BigDecimal total = details.stream()
                .map(detail -> detail.getPrice().multiply(new BigDecimal(detail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 创建订单
        Order order = new Order();
        order.setUserId(Math.toIntExact(userId));
        order.setTotalAmount(total);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");
        orderMapper.insertOrder(order);

        // 创建订单详细信息
        orderMapper.insertOrderDetails(order, details);

        MKLConfig config = new MKLConfig();
        WXPay wxpay = new WXPay(config);

        HashMap<String, String> data = new HashMap<>();
        data.put("appid", "");
        data.put("mch_id", "JSAPI");

        data.put("body", "美客来汉堡店-餐饮");
        data.put("out_trade_no", String.valueOf(order.getOrderId()));

        data.put("sign", "CNY");
        data.put("nonce_str", "CNY");

        data.put("total_fee", String.valueOf(total));
        data.put("spbill_create_ip", "127.0.0.1");
        data.put("notify_url", "");
        data.put("trade_type", "JSAPI");  // JSAPI - JSAPI支付（或小程序支付）



        try {
            // 向微信支付API发送支付请求
            Map<String, String> resp = wxpay.unifiedOrder(data);
            Map<String, String> payMap = new HashMap<>();
            // 处理返回结果
            if ("SUCCESS".equals(resp.get("return_code"))) {
                // 返回前端调起支付所需参数
                payMap.put("appId", config.getAppID());
                payMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
                payMap.put("nonceStr", resp.get("nonce_str"));
                payMap.put("package", "prepay_id=" + resp.get("prepay_id"));
                payMap.put("signType", "MD5");
                // 对返回给前端的参数进行签名
                String paySign = generatePaySign(payMap, config.getKey());
                payMap.put("paySign", paySign);
            } else {
                throw new Exception("创建微信支付订单失败");
            }
           // return payMap;
            return payMap;
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }
    private String generatePaySign(Map<String, String> data, String key) {
        // 对数据按照键名进行字典序排序
        String sortedString = data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((s1, s2) -> s1 + "&" + s2)
                .orElse("");

        // 将商户API密钥拼接在排序后的字符串末尾
        sortedString += "&key=" + key;

        // 使用MD5进行签名
        return MD5Util.MD5Encode(sortedString, "UTF-8").toUpperCase();
    }
}
