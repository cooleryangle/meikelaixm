package com.meikelai.service;

public interface ITokenService {
    public String generateToken(String openId);
    public String parseToken(String jwtToken);

}
