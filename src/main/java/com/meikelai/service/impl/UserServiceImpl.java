package com.meikelai.service.impl;

import com.meikelai.entity.Session;
import com.meikelai.entity.User;
import com.meikelai.entity.WechatUser;
import com.meikelai.mapper.SessionMapper;
import com.meikelai.mapper.UserMapper;
import com.meikelai.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper; // 假设使用MyBatis Plus
    @Autowired
    private SessionMapper sessionMapper;
    @Autowired
    private FileStorageServiceImpl fileStorageService; // 用于文件存
    @Value("${file.storage.location}")
    private String fileLocation;

    public WechatUser processWechatLogin(String wechatData) {
        try {
            // 解析微信数据
            JSONObject jsonObject = new JSONObject(wechatData);
            String openId = jsonObject.getString("openid");
            String sessionKey = jsonObject.getString("session_key");

            // 根据openId查找或创建用户
            User user = userMapper.selectByOpenId(openId);
            if (user == null) {
                user = new User();
                user.setWechatOpenId(openId);
                userMapper.insert(user);
            }

            // 创建或更新Session
            Session session = sessionMapper.selectByUserId(user.getUserId());
            if (session == null) {
                session = new Session();
                session.setOpenId(user.getWechatOpenId());
                session.setUserId(user.getUserId());
                session.setSessionKey(sessionKey);

                sessionMapper.insert(session);
            } else {
                session.setSessionKey(sessionKey);
                sessionMapper.updateById(session);
            }

            // 创建并返回用户信息DTO
            WechatUser wechatUserDto = new WechatUser();
            wechatUserDto.setOpenId(user.getWechatOpenId());
            wechatUserDto.setSessionKey(session.getSessionKey());
            wechatUserDto.setNickname(user.getNickname());
            wechatUserDto.setAvatarUrl("http://127.0.0.1:8081/"+fileLocation+"/"+user.getAvatarUrl());
            // 设置其他所需字段

            return wechatUserDto;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
@Override
    // 更新用户昵称
    public void updateNickname(String nickname, String openId) {
        User user = userMapper.selectByOpenId(openId);
        user.setNickname(nickname);
        userMapper.updateById(user);
    }

    // 保存头像
    @Override
    public String saveAvatar(MultipartFile avatar, String openId) {
        String avatarFilename = fileStorageService.storeFile(avatar); // 保存文件
        User user = userMapper.selectByOpenId(openId);

        user.setAvatarUrl(avatarFilename); // 更新头像URL
        userMapper.updateById(user);
        return avatarFilename; // 返回头像的文件名或URL
    }

    @Override
    public User selectByOpenId(String openId) {
        return userMapper.selectByOpenId(openId);
    }
}
