package com.example.imagemvvmretrofitflickerapi.dao;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = "PhotoId", unique = true)})
public class Photo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AutoId")
    private int autoId;

    @ColumnInfo(name = "PhotoId")
    private String photoId;

    @ColumnInfo(name = "PhotoUrl")
    private String photoUrl;

    public Photo(String photoId, String photoUrl) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
