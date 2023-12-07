package com.meikelai.entity;

public class WechatUser {
    private String openId;
    private String sessionKey;
    // 可以添加其他字段，如昵称、头像URL等，根据业务需求
    private String nickname;

    private String avatarUrl;
    // 构造函数
    public WechatUser() {
    }

    public WechatUser(String openId, String sessionKey, String nickname, String avatarUrl) {
        this.openId = openId;
        this.sessionKey = sessionKey;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    // Getters 和 Setters
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    // 可以根据需要添加其他字段的Getters和Setters

    // toString 方法，如果需要的话
    @Override
    public String toString() {
        return "WechatUserDto{" +
                "openId='" + openId + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                '}';
    }
}
