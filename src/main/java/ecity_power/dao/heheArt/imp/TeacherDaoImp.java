package ecity_power.dao.heheArt.imp;

import ecity_power.dao.heheArt.TeacherDao;
import ecity_power.model.heheArt.Teacher;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDaoImp extends heheArtBaseDao implements TeacherDao {

    @Override
    public List<Teacher> getTeachers() throws SQLException {
        List<Teacher> items = new ArrayList<Teacher>();
        String selectSql = String.format("select * from Teacher");

        try (Connection connection = DriverManager.getConnection(dbConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    while (rs.next()) {
                        int i = 1;
                        Teacher item = new Teacher();
                        item.setId(rs.getString(i++));
                        item.setName(rs.getString(i++));
                        item.setAvatar(rs.getString(i++));
                        item.setBrief(rs.getString(i++));
                        item.setIntroduction(rs.getString(i++));
                        items.add(item);
                    }
                }
            }
        }

        return items;
    }

    @Override
    public Teacher getTeacherById(String id) throws SQLException {
        String selectSql = String.format("select * from Teacher where Id = '%s'", id);

        Teacher item = new Teacher();

        try (Connection connection = DriverManager.getConnection(dbConnectString)) {
            try (Statement stmt = connection.createStatement()) {
                try(ResultSet rs = stmt.executeQuery(selectSql)) {
                    if (rs.next()) {
                        int i = 1;
                        item.setId(rs.getString(i++));
                        item.setName(rs.getString(i++));
                        item.setAvatar(rs.getString(i++));
                        item.setBrief(rs.getString(i++));
                        item.setIntroduction(rs.getString(i++));
                    }
                }
            }
        }

        return item;
    }

    @Override
    public void insertTeacher(Teacher teacher) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbConnectString)){
            String insertSql = "insert into Teacher values(?,?,?,?,?)";
            try(PreparedStatement ps = connection.prepareStatement(insertSql)) {
                int i = 1;
                ps.setString(i++, teacher.getId());
                ps.setString(i++, teacher.getName());
                ps.setString(i++, teacher.getAvatar());
                ps.setString(i++, teacher.getBrief());
                ps.setString(i++, teacher.getIntroduction());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbConnectString)){
            String insertSql = "update Teacher set Name=?, Avatar=?, Brief = ?, Introduction=? where id = ?";
            try(PreparedStatement ps = connection.prepareStatement(insertSql)) {
                int i = 1;
                ps.setString(i++, teacher.getName());
                ps.setString(i++, teacher.getAvatar());
                ps.setString(i++, teacher.getBrief());
                ps.setString(i++, teacher.getIntroduction());
                ps.setString(i++, teacher.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void deleteTeacher(String id) throws SQLException {
        String deleteSql = String.format("update Teacher set IsDel=1 where id = '%s'", id);
        delete(deleteSql);
    }
}
