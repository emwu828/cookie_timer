package com.onramp.android.takehome;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TimerService extends Service {
    private static final String TAG = "TimerService";

    private final IBinder mBinder = new TimerBinder();
    private Handler mHandler;
    private int mProgress, mStartValue, mStopValue;
    private Boolean mIsPaused;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: starts");

        super.onCreate();
        mHandler = new Handler();
        mIsPaused = true;
        mStartValue = 0;
        mStopValue = 0;
        mProgress = mStartValue;
    }

    // Service's onBind method
    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return mBinder; }

    // Binder class
    public class TimerBinder extends Binder {
        TimerService getService() { return TimerService.this; }
    }

    // StartTimer method
    public void startTimer(){
        Log.d(TAG, "startTimer: starts");

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(mProgress <= mStopValue || mIsPaused){
                    Log.d(TAG, "run: removing callbacks");
                    mHandler.removeCallbacks(this); // remove callbacks from runnable
                    pauseTimer();
                }
                else{
                    Log.d(TAG, "run: progress: " + mProgress);
                    mProgress -= 1; // increment the progress
                    mHandler.postDelayed(this, 1000); // continue incrementing
                }
            }
        };
        mHandler.postDelayed(runnable, 1000);
    }

    // SetTimer Method
    public void setTimer(Integer timerVal) {
        Log.d(TAG, "setTimer: starts");

        mStartValue = timerVal*60;
        resetTask();
    }

    public void resetTask() { mProgress = mStartValue; }

    public Boolean getIsPaused(){
        return mIsPaused;
    }

    public int getProgress(){
        return mProgress;
    }

    public int getStopValue(){
        return mStopValue;
    }

    public void pauseTimer(){
        mIsPaused = true;
    }

    public void unPauseTimer(){
        mIsPaused = false;
        startTimer();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(TAG, "onTaskRemoved: starts");
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: starts");
        super.onDestroy();
    }

}
