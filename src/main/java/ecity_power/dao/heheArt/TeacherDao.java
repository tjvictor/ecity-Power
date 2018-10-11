package ecity_power.dao.heheArt;

import ecity_power.model.heheArt.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {

    List<Teacher> getTeachers() throws SQLException;

    Teacher getTeacherById(String id) throws SQLException;

    void insertTeacher(Teacher teacher) throws SQLException;

    void updateTeacher(Teacher teacher) throws SQLException;

    void deleteTeacher(String id) throws SQLException;
}
