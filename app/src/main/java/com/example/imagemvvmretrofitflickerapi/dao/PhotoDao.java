package com.example.imagemvvmretrofitflickerapi.dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;



@Dao
public interface PhotoDao {
    @Query("Select * from Photo limit 50 Offset (Select Count(*) from Photo) - 50")
    LiveData<List<Photo>> getLastAddedPhotos();

    @Query("Select * from Photo limit :startIndex,:rangeCount")
    List<Photo> getPhotosInRange(int startIndex, int rangeCount);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertPhoto(List<Photo> photos);

    @Query("Delete from Photo")
    void deleteAllPhotos();
}
