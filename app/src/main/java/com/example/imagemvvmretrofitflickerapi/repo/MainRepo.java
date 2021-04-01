package com.example.imagemvvmretrofitflickerapi.repo;


import android.content.Context;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.imagemvvmretrofitflickerapi.api.ApiClient;
import com.example.imagemvvmretrofitflickerapi.api.ApiInterface;
import com.example.imagemvvmretrofitflickerapi.common.DaoManager;
import com.example.imagemvvmretrofitflickerapi.common.ResponseToDao;
import com.example.imagemvvmretrofitflickerapi.common.ResultCallbackListener;
import com.example.imagemvvmretrofitflickerapi.dao.Photo;
import com.example.imagemvvmretrofitflickerapi.dao.PhotoDao;
import com.example.imagemvvmretrofitflickerapi.responsemodel.FlickrRootResponseModel;
import com.example.imagemvvmretrofitflickerapi.responsemodel.PhotoResponseModel;
import com.example.imagemvvmretrofitflickerapi.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainRepo {

    private static final String LOGGER_TAG = MainRepo.class.getSimpleName();
    private static final String ROOT_PATH = "https://api.flickr.com/";
    private static final String API_KEY = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s";
    private static final String FORMAT = "json";
    private PhotoDao mPhotoDao;
    private LiveData<List<Photo>> mLastAddedPhotos = null;
    private final Context mContext;

    public MainRepo(Context context) {
        this.mContext = context;
        mPhotoDao = DaoManager.getInstance(context).getAppDatabase().photoDao();
    }

    public void getPhotosFromWeb(String tag, final ResultCallbackListener<FlickrRootResponseModel, Throwable> listener,
                                 int page, int perPage) {
        Retrofit client = ApiClient.getClient(ROOT_PATH);
        ApiInterface apiInterface = client.create(ApiInterface.class);
        Call<FlickrRootResponseModel> randomPhotos = apiInterface.getRandomPhotos("flickr.photos.search", API_KEY, tag
                , page, perPage, FORMAT, 1);

        Log.d(LOGGER_TAG, "Enqueueing fetch request");
        randomPhotos.enqueue(new Callback<FlickrRootResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<FlickrRootResponseModel> call, @NonNull Response<FlickrRootResponseModel> response) {
                if (response.isSuccessful()) {
                    insertDataInDb(response.body());
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(new Throwable("Unable to load data"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<FlickrRootResponseModel> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable);
            }
        });
    }

    private void insertDataInDb(FlickrRootResponseModel body) {
        if (body != null && body.getPhotosResponseModel().getPhotoResponseModel() != null) {
            final List<Photo> daoPhotos = new ArrayList<>();
            List<PhotoResponseModel> photoResponseModel = body.getPhotosResponseModel().getPhotoResponseModel();
            int from = photoResponseModel.size() - Math.min(photoResponseModel.size(), MainViewModel.INCREMENT_COUNT);
            int to = from + Math.min(photoResponseModel.size(), MainViewModel.INCREMENT_COUNT);
            for (PhotoResponseModel item : photoResponseModel.subList(from, to)) {
                daoPhotos.add(ResponseToDao.toDao(item));
            }

            Log.d(LOGGER_TAG, "Inserting records in db:" + daoPhotos.size() + " from: " + from + " to: " + to);
            DaoManager.getInstance(mContext).submitQuery(new Runnable() {
                @Override
                public void run() {
                    mPhotoDao.insertPhoto(daoPhotos);
                }
            });
        }
    }

    public List<Photo> getPhotosInRange(int startIndex, int rangeCount) {
        return mPhotoDao.getPhotosInRange(startIndex, rangeCount);
    }

    public LiveData<List<Photo>> getLastAddedPhotos() {
        if (mLastAddedPhotos == null) {
            mLastAddedPhotos = mPhotoDao.getLastAddedPhotos();
        }

        return mLastAddedPhotos;
    }

    public void deleteAllPhotos() {
        mPhotoDao.deleteAllPhotos();
    }
}
