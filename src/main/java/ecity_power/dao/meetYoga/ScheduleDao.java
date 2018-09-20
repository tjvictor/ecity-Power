package ecity_power.dao.meetYoga;

import ecity_power.model.meetYoga.Schedule;
import ecity_power.model.meetYoga.ScheduleExt;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ScheduleDao {
    List<Schedule> getSchedules() throws SQLException;

    List<Schedule> getSchedulesByFilter(String courseId, String teacherId, String startDate, String endDate) throws SQLException, ParseException;

    Schedule getFullScheduleById(String id) throws SQLException;

    List<Schedule> getFullScheduleByDate(String date) throws SQLException;

    void insertSchedule(Schedule schedule) throws SQLException;

    void updateSchedule(Schedule schedule) throws SQLException;

    void deleteSchedule(String id) throws SQLException;

    Schedule getScheduleById(String scheduleId) throws SQLException;

    List<ScheduleExt> getOneDayScheduledCourses(String dateStr, String memberId) throws SQLException;
}
