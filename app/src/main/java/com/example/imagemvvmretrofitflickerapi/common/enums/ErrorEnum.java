package com.example.imagemvvmretrofitflickerapi.common.enums;


import androidx.annotation.StringRes;

import com.example.imagemvvmretrofitflickerapi.R;

public enum ErrorEnum {
    UNABLE_TO_FETCH_DATA(R.string.error_unable_fetch_data),
    INVALID_DATA(R.string.error_invalid_data);

    @StringRes
    int mErrorResource;

    ErrorEnum(int errorResource) {
        this.mErrorResource = errorResource;
    }

    @StringRes
    public int getErrorResource() {
        return this.mErrorResource;
    }
}
