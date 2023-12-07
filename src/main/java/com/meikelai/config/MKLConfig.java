package com.meikelai.config;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

public class MKLConfig implements WXPayConfig {
    @Override
    public String getAppID() {
        return null;
    }

    @Override
    public String getMchID() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
