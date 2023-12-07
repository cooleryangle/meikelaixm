package com.meikelai.service;

import com.meikelai.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meikelai.entity.WechatUser;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Le
 * @since 2023-11-24
 */
public interface IUserService extends IService<User> {
    public WechatUser processWechatLogin(String wechatData);

    void updateNickname(String openId, String nickname);

    String saveAvatar(MultipartFile avatar, String openId);

    User selectByOpenId(String openId);
}
