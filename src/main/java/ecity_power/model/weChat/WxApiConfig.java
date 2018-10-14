package ecity_power.model.weChat;

import ecity_power.utility.WeChatUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WxApiConfig {
    private String debug;
    private String appId;
    private String timestamp;
    private String nonceStr;
    private String signature;
    private List<String> jsApiList;

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public WxApiConfig(){

    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public List<String> getJsApiList() {
        return jsApiList;
    }

    public void setJsApiList(List<String> jsApiList) {
        this.jsApiList = jsApiList;
    }
}
