package ecity_power.dao.meetYoga;

import ecity_power.model.meetYoga.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsDao {

    List<News> getAllNews() throws SQLException;

    List<News> getAllNewsBrief() throws SQLException;

    News getNewsById(String id) throws SQLException;

    void insertNews(News item) throws SQLException;

    void updateNews(News item) throws SQLException;

    void deleteNews(String id) throws SQLException;
}
