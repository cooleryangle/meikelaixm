package com.meikelai.service.impl;

import com.meikelai.entity.WechatUser;
import com.meikelai.service.IUserService;
import com.meikelai.service.IWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WechatServiceImpl implements IWechatService {
    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Autowired
    private IUserService userService;

    public WechatUser loginWithWechat(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, appSecret, code);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            // 解析响应，获取openId和sessionKey
            // 假设有一个方法解析这些信息并返回一个DTO
            return userService.processWechatLogin(responseBody);
        }
        return null;
    }
}
