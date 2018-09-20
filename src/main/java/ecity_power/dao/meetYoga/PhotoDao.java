package ecity_power.dao.meetYoga;

import ecity_power.model.meetYoga.Photo;
import ecity_power.model.meetYoga.PhotoWall;

import java.sql.SQLException;
import java.util.List;

public interface PhotoDao {
    List<PhotoWall> getAllPhotoWallEntities() throws SQLException;

    List<PhotoWall> getAllPhotoWallWithPhotos(int pageNumber, int pageSize) throws SQLException;

    List<Photo> getPhotoByPhotoWallId(String photoWallId) throws SQLException;

    int getPhotoWallTotalCount() throws SQLException;

    void addPhoto(Photo item) throws SQLException;

    void deletePhoto(String id) throws SQLException;

    void addPhotoWall(PhotoWall item) throws SQLException;

    void updatePhotoWall(PhotoWall item) throws SQLException;

    void deletePhotoWall(String id) throws SQLException;
}
