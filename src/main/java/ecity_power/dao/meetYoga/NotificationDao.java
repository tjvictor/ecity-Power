package ecity_power.dao.meetYoga;


import ecity_power.model.meetYoga.Notification;

import java.sql.SQLException;
import java.util.List;

public interface NotificationDao {

    List<Notification> getNotifications() throws SQLException;

    Notification getNotificationById(String id) throws SQLException;

    List<Notification> getTopNotificationBriefs(int topCount) throws SQLException;

    void insertNotification(Notification item) throws SQLException;

    void updateNotification(Notification item) throws SQLException;

    void deleteNotification(String id) throws SQLException;
}
