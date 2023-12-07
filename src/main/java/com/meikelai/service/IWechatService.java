package com.meikelai.service;

import com.meikelai.entity.WechatUser;

public interface IWechatService {
    public WechatUser loginWithWechat(String code);
}
