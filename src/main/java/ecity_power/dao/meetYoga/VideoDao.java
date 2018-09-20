package ecity_power.dao.meetYoga;

import ecity_power.model.meetYoga.Video;

import java.sql.SQLException;
import java.util.List;

public interface VideoDao {

    List<Video> getVideos() throws SQLException;
}
