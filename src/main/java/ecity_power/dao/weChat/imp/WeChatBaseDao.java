package ecity_power.dao.weChat.imp;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class WeChatBaseDao {

    @Value("${db.weChatActivity.connectString}")
    protected String dbActivityConnectString;

    public int insert(String insertSql) throws SQLException {
        return update(insertSql);
    }

    public int update(String updateSql) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbActivityConnectString)){
            try(Statement stmt = connection.createStatement()) {
                return stmt.executeUpdate(updateSql);
            }
        }
    }

    public int delete(String deleteSql) throws SQLException {
        return update(deleteSql);
    }

    public String escapeString(String item){
        if(StringUtils.isEmpty(item))
            return "";
        return StringEscapeUtils.escapeHtml4(item);
    }
}
