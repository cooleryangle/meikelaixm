package com.meikelai.controller;

import com.meikelai.entity.WechatUser;
import com.meikelai.service.ITokenService;
import com.meikelai.service.IUserService;
import com.meikelai.service.IWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IWechatService wechatService;

    @Autowired
    private IUserService userService; // 注入JWT令牌生成器
    @Autowired
    private ITokenService tokenService; // 注入JWT令牌生成器


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> payload) {
        String code = payload.get("code");
        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Code is missing");
        }
        WechatUser wechatUser = wechatService.loginWithWechat(code);
        if (wechatUser != null) {
            // 创建JWT令牌
            String token = tokenService.generateToken(wechatUser.getOpenId());

            // 创建并返回响应体，包含JWT令牌和用户信息
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userInfo", wechatUser);
            System.out.println("loginToken: " + token);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }

    // 更新用户昵称

    @PostMapping("/updateNickname")
    public ResponseEntity<?> updateNickname(@RequestBody Map<String, String> payload,
                                            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7); // 去掉Bearer前缀
        String openId = tokenService.parseToken(token);

        // 使用openId更新用户昵称
        userService.updateNickname(payload.get("nickname"), openId);

        return ResponseEntity.ok().body("昵称更新成功");
    }

    // 上传用户头像
    @PostMapping("/uploadAvatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam("avatar") MultipartFile avatar,  @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7); // 去掉Bearer前缀 // 保存头像文件并更新用户头像URL
        String openId = tokenService.parseToken(token);
        String avatarUrl = userService.saveAvatar(avatar, openId);
        return ResponseEntity.ok().body(avatarUrl);
    }

}
