package ecity_power.dao.heheArt;

import ecity_power.model.heheArt.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    List<Course> getCourses() throws SQLException;

    Course getCourseById(String id) throws SQLException;

    void insertCourse(Course course) throws SQLException;

    void updateCourse(Course course) throws SQLException;

    void deleteCourse(String id) throws SQLException;
}
