package ecity_power.model.weChat;

import java.util.List;

/**
 * 类名: SNSUserInfo </br>
 * 描述: 通过网页授权获取的用户信息 </br>
 */
public class SNSUserInfo {
    // 用户标识
    private String openId;
    // 用户昵称
    private String nickName;
    // 性别（1是男性，2是女性，0是未知）
    private String sex;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 用户头像链接
    private String headImgUrl;
    // 用户特权信息
    private List<String> privilegeList;

    private String privilegeString;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public List<String> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<String> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public String getPrivilegeString() {
        if(privilegeList == null || privilegeList.size() == 0){
            return "";
        }
        return String.join(",", privilegeList);
    }
}