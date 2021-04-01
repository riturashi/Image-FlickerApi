package com.example.imagemvvmretrofitflickerapi.common;


public interface ResultCallbackListener<R, E> {
    void onSuccess(R response);

    void onFailure(E error);
}
