package com.example.imagemvvmretrofitflickerapi.common;


import androidx.annotation.Nullable;

import com.example.imagemvvmretrofitflickerapi.dao.Photo;
import com.example.imagemvvmretrofitflickerapi.model.PhotoListModel;

public class DaoToUi {

    @Nullable
    public static PhotoListModel toUi(Photo photo) {
        PhotoListModel photoListModel = null;
        if (photo != null) {
            photoListModel = new PhotoListModel();
            photoListModel.setUrl(photo.getPhotoUrl());
            photoListModel.setId(photo.getPhotoId());
        }

        return photoListModel;
    }
}
