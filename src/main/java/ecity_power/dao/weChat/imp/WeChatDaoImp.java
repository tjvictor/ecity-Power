package ecity_power.dao.weChat.imp;

import ecity_power.dao.weChat.WeChatDao;
import ecity_power.model.weChat.Activity;
import ecity_power.model.weChat.Activity_Participate;
import ecity_power.model.weChat.Activity_Register;
import ecity_power.model.weChat.OauthToken;
import ecity_power.model.weChat.SNSUserInfo;

import ecity_power.utility.DateUtils;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeChatDaoImp extends WeChatBaseDao implements WeChatDao {

    @Override
    public void insertSNSUserInfo(SNSUserInfo item) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)){
            String insertSql = "insert into SNSUserInfo values(?,?,?,?,?,?,?,?)";
            try(PreparedStatement ps = connection.prepareStatement(insertSql)) {
                int i = 1;
                ps.setString(i++, item.getOpenId());
                ps.setString(i++, item.getNickName());
                ps.setString(i++, item.getSex());
                ps.setString(i++, item.getCountry());
                ps.setString(i++, item.getProvince());
                ps.setString(i++, item.getCity());
                ps.setString(i++, item.getHeadImgUrl());
                ps.setString(i++, item.getPrivilegeString());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void insertActivityRegister(Activity_Register item) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)){
            String insertSql = "insert into Activity_Register values(?,?,?,?,?)";
            try(PreparedStatement ps = connection.prepareStatement(insertSql)) {
                int i = 1;
                ps.setString(i++, item.getId());
                ps.setString(i++, item.getActivityId());
                ps.setString(i++, item.getRegisterId());
                ps.setString(i++, item.getRegisterName());
                ps.setString(i++, item.getDate());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void insertActivityParticipate(Activity_Participate item) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)){
            String insertSql = "insert into Activity_Participate values(?,?,?,?,?,?)";
            try(PreparedStatement ps = connection.prepareStatement(insertSql)) {
                int i = 1;
                ps.setString(i++, item.getId());
                ps.setString(i++, item.getActivityRegisterId());
                ps.setString(i++, item.getParticipateId());
                ps.setString(i++, item.getParticipateName());
                ps.setString(i++, item.getWeight());
                ps.setString(i++, item.getDate());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Activity getActivityById(String id) throws SQLException {
        Activity item = new Activity();
        String selectSql = String.format("select Id, Name, PublishPage, RegisterPage, ParticipatePage, Date from Activity where id = '%s';", id);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    if (rs.next()) {
                        int i = 1;
                        item.setId(rs.getString(i++));
                        item.setName(rs.getString(i++));
                        item.setPublishPage(rs.getString(i++));
                        item.setRegisterPage(rs.getString(i++));
                        item.setParticipatePage(rs.getString(i++));
                        item.setDate(rs.getString(i++));
                    }
                }
            }
        }

        return item;
    }

    @Override
    public List<Activity_Register> getActivityRegistersByActivityId(String activityId) throws SQLException {
        List<Activity_Register> items = new ArrayList<Activity_Register>();

        String selectSql = String.format("select Id, ActivityId, RegisterId, RegisterName, Date from Activity_Register where ActivityId = '%s';", activityId);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    while (rs.next()) {
                        int i = 1;
                        Activity_Register item = new Activity_Register();
                        item.setId(rs.getString(i++));
                        item.setActivityId(rs.getString(i++));
                        item.setRegisterId(rs.getString(i++));
                        item.setRegisterName(rs.getString(i++));
                        item.setDate(rs.getString(i++));

                        items.add(item);
                    }
                }
            }
        }

        return items;
    }

    @Override
    public Activity_Register getActivityRegisterByActivityIdAndRegisterId(String activityId, String registerId) throws SQLException {
        String selectSql = String.format("select Id, ActivityId, RegisterId, RegisterName, Date from Activity_Register where ActivityId = '%s' and RegisterId = '%s';", activityId, registerId);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    while (rs.next()) {
                        int i = 1;
                        Activity_Register item = new Activity_Register();
                        item.setId(rs.getString(i++));
                        item.setActivityId(rs.getString(i++));
                        item.setRegisterId(rs.getString(i++));
                        item.setRegisterName(rs.getString(i++));
                        item.setDate(rs.getString(i++));

                        return item;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Activity_Register getActivityRegisterById(String id) throws SQLException {
        String selectSql = String.format("select Id, ActivityId, RegisterId, RegisterName, Date from Activity_Register where Id = '%s';", id);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    while (rs.next()) {
                        int i = 1;
                        Activity_Register item = new Activity_Register();
                        item.setId(rs.getString(i++));
                        item.setActivityId(rs.getString(i++));
                        item.setRegisterId(rs.getString(i++));
                        item.setRegisterName(rs.getString(i++));
                        item.setDate(rs.getString(i++));

                        return item;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public List<Activity_Participate> getActivityParticipatesByActivityRegisterId(String activityRegisterId) throws SQLException {
        List<Activity_Participate> items = new ArrayList<Activity_Participate>();

        String selectSql = String.format("select Id, ActivityRegisterId, ParticipateId, ParticipateName, Weight, Date from Activity_Participate where ActivityRegisterId = '%s';", activityRegisterId);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    while (rs.next()) {
                        int i = 1;
                        Activity_Participate item = new Activity_Participate();
                        item.setId(rs.getString(i++));
                        item.setActivityRegisterId(rs.getString(i++));
                        item.setParticipateId(rs.getString(i++));
                        item.setParticipateName(rs.getString(i++));
                        item.setWeight(rs.getString(i++));
                        item.setDate(rs.getString(i++));

                        items.add(item);
                    }
                }
            }
        }

        return items;
    }

    @Override
    public Activity_Participate getActivityParticipatesByActivityRegisterIdAndParticipateId(String activityRegisterId, String participateId) throws SQLException {
        String selectSql = String.format("select Id, ActivityRegisterId, ParticipateId, ParticipateName, Weight, Date from Activity_Participate where ActivityRegisterId = '%s' and ParticipateId = '%s';", activityRegisterId, participateId);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    while (rs.next()) {
                        int i = 1;
                        Activity_Participate item = new Activity_Participate();
                        item.setId(rs.getString(i++));
                        item.setActivityRegisterId(rs.getString(i++));
                        item.setParticipateId(rs.getString(i++));
                        item.setParticipateName(rs.getString(i++));
                        item.setWeight(rs.getString(i++));
                        item.setDate(rs.getString(i++));

                        return item;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public boolean isRegisterExist(String activityId, String registerId) throws SQLException {
        String selectSql = String.format("select count(0) from Activity_Register where ActivityId = '%s' and RegisterId = '%s';" ,activityId, registerId);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    if (rs.next()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean isParticipateExist(String activityRegisterId, String participateId) throws SQLException {
        String selectSql = String.format("select count(0) from Activity_Participate where ActivityRegisterId = '%s' and ParticipateId = '%s';" ,activityRegisterId, participateId);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    if (rs.next()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public OauthToken getOauthTokenByOpenId(String openId) throws SQLException {
        OauthToken item = new OauthToken();
        String selectSql = String.format("SELECT OpenId, AccessToken, ExpiresIn, RefreshToken, Scope FROM OauthToken where OpenId = '%s';", openId);
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    if (rs.next()) {
                        int i = 1;
                        item.setOpenId(rs.getString(i++));
                        item.setAccessToken(rs.getString(i++));
                        item.setExpiresIn(rs.getString(i++));
                        item.setRefreshToken(rs.getString(i++));
                        item.setScope(rs.getString(i++));
                    }
                }
            }
        }

        return item;
    }

    @Override
    public void replaceOauthToken(OauthToken item) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)){
            String replaceStr = "replace into OauthToken values(?,?,?,?,?,?);";
            try(PreparedStatement ps = connection.prepareStatement(replaceStr)) {
                int i = 1;
                ps.setString(i++, item.getOpenId());
                ps.setString(i++, item.getAccessToken());
                ps.setString(i++, item.getExpiresIn());
                ps.setString(i++, item.getRefreshToken());
                ps.setString(i++, item.getScope());
                ps.setString(i++, DateUtils.getCurrentDateTime());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void replaceSNSUserInfo(SNSUserInfo item) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)){
            String replaceStr = "replace into SNSUserInfo values(?,?,?,?,?,?,?,?,?);";
            try(PreparedStatement ps = connection.prepareStatement(replaceStr)) {
                int i = 1;
                ps.setString(i++, item.getOpenId());
                ps.setString(i++, item.getNickName());
                ps.setString(i++, item.getSex());
                ps.setString(i++, item.getCountry());
                ps.setString(i++, item.getProvince());
                ps.setString(i++, item.getCity());
                ps.setString(i++, item.getHeadImgUrl());
                ps.setString(i++, item.getPrivilegeString());
                ps.setString(i++, DateUtils.getCurrentDateTime());
                ps.executeUpdate();
            }
        }
    }
}
