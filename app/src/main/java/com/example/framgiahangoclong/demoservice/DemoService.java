package com.example.framgiahangoclong.demoservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

public class DemoService extends Service {

    private IBinder mBinder = new LocalBinder();
    private OnDataChangeListener mListener;
    private Handler mHandler = new Handler();
    private int count = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public DemoService getService() {
            return DemoService.this;
        }
    }

    public void setListener(OnDataChangeListener dataChangeListener) {

        mListener = dataChangeListener;
    }

    public void passData() {
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, 1000);
    }

    public void stopPassData() {
        mHandler.removeCallbacks(mRunnable);
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mListener.OnDataChanged(count);
            count++;
            mHandler.postDelayed(this, 1000);
        }
    };
}
