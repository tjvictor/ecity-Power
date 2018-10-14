package ecity_power.model.weChat;

import ecity_power.utility.WeChatUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSApiTicket {

    private String appId;
    private String ticket;
    private String nonceStr;
    private String timestamp;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature(String url) throws NoSuchAlgorithmException {
//        List<String> items = new ArrayList<String>();
//        items.add(nonceStr);
//        items.add(ticket);
//        items.add(timestamp);
//        items.add(url);1414587457
//        Collections.sort(items);

        String encryptedString = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s",
                ticket,nonceStr,timestamp,url);

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(encryptedString.toString().getBytes());
        return WeChatUtil.byteToStr(digest);
    }


}
