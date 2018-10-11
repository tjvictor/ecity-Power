package ecity_power.dao.heheArt;

import ecity_power.model.heheArt.Video;

import java.sql.SQLException;
import java.util.List;

public interface VideoDao {

    List<Video> getVideos() throws SQLException;
}
