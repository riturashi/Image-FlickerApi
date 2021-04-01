package com.example.imagemvvmretrofitflickerapi.common;


import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.room.Room;

import com.example.imagemvvmretrofitflickerapi.dao.AppDatabase;


public class DaoManager {
    private AppDatabase mAppDatabase;
    private static DaoManager mInstance;
    private Handler mHandler;

    private DaoManager(Context context) {
        mAppDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "main-database")
                .build();
        HandlerThread handlerThread = new HandlerThread(DaoManager.class.getSimpleName());
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    public static DaoManager getInstance(Context context) {
        if (mInstance != null) {
            return mInstance;
        } else {
            synchronized (DaoManager.class) {
                if (mInstance == null) {
                    mInstance = new DaoManager(context);
                }
                return mInstance;
            }
        }
    }

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    public void submitQuery(Runnable query) {
        mHandler.post(query);
    }
}
