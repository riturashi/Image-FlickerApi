package com.example.imagemvvmretrofitflickerapi.responsemodel;

import com.google.gson.annotations.SerializedName;


public class FlickrRootResponseModel {
    @SerializedName("photos")
    private PhotosResponseModel mPhotosResponseModel;

    public PhotosResponseModel getPhotosResponseModel() {
        return mPhotosResponseModel;
    }

    public void setPhotosResponseModel(PhotosResponseModel photosResponseModel) {
        this.mPhotosResponseModel = photosResponseModel;
    }
}
