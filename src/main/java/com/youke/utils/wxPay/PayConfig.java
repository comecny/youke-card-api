package com.youke.utils.wxPay;

import com.youke.utils.sdkpay.IWXPayDomain;
import com.youke.utils.sdkpay.WXPayConfig;
import com.youke.utils.sdkpay.WXPayConstants;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public  class PayConfig extends WXPayConfig {


    private byte[] certData;

    public PayConfig() throws Exception{

    }

    @Override
    public String getAppID() {
        return "wxe01b85db45cd14c9";
    }

    @Override
    public String getMchID() {
        return "1551533391";
    }

    @Override
    protected String getKey() {
        return "8374uieryeujkhk5y478hdnbasdjpqoi";
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;

    }




}
