package com.onramp.android.takehome.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.onramp.android.takehome.TimerService;

public class TimerViewModel extends ViewModel {
    private static final String TAG = "TimerViewModel";

    // MutableLiveData Objects
    private MutableLiveData<Boolean> mIsTimerUpdating = new MutableLiveData<>();
    private MutableLiveData<TimerService.TimerBinder> mBinder = new MutableLiveData<>();

    // Service Connection
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: connected to service");
            TimerService.TimerBinder binder = (TimerService.TimerBinder) iBinder;
            mBinder.postValue(binder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: disconnected from service");
            mBinder.postValue(null);
        }
    };

    public ServiceConnection getServiceConnection() { return mServiceConnection; }

    //LiveData Objects
    public LiveData<TimerService.TimerBinder> getBinder() { return mBinder; }
    public LiveData<Boolean> getIsTimerUpdating() { return mIsTimerUpdating; }

    // Set timer state
    public void setIsTimerUpdating(boolean isTimerUpdating) {
        Log.d(TAG, "setIsTimerUpdating: starts");

        mIsTimerUpdating.postValue(isTimerUpdating);
    }
}
