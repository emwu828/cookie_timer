package com.onramp.android.takehome;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onramp.android.takehome.viewmodels.TimerViewModel;

public class TimerActivity extends AppCompatActivity implements TimerDialogFragment.OnInputListener {
    private static final String TAG = "TimerActivity";

    // UI Components
    private TextView mTextView;
    private Button mButton;

    // Vars
    private TimerService mService;
    public int mInput;

    // ViewModels
    private TimerViewModel mViewModel;

    // Callback function to obtain user input value from TimerDialogFragment
    // Updates input value to UI and TimerService
    @Override
    public void sendInput(Integer input) {
        Log.d(TAG, "sendInput: starts");

        mInput = input;
        mService.setTimer(mInput);
        mTextView.setText(String.format("%02d", mInput) + ":00");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = findViewById(R.id.timer_toolbar);
        setSupportActionBar(toolbar);

        // Inflate UI components
        mTextView = findViewById(R.id.timer_time);
        mButton = findViewById(R.id.timer_button);

        // Instantiate new TimerViewModel
        mViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);

        // Set ViewModel Observers
        setObservers();

        // Set button clickListener
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleUpdates();
            }
        });

        // Instantiate new TimerDialogFragment
        TimerDialogFragment timerDialogFragment = new TimerDialogFragment();
        timerDialogFragment.show(getSupportFragmentManager(), "TimerDialogFragment");

        // Disable back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void toggleUpdates() {
        Log.d(TAG, "toggleUpdates: starts");

        if (mService != null) {
            if (mService.getProgress() == mService.getStopValue()) {
                Log.d(TAG, "toggleUpdates: mService.getProgress() == 0");

                mService.resetTask();
                mButton.setText("Start");
            } else {
                Log.d(TAG, "toggleUpdates: mService.getProgress() != 0)");

                if (mService.getIsPaused()) {
                    Log.d(TAG, "toggleUpdates: Timer Paused");

                    mService.unPauseTimer();
                    mViewModel.setIsTimerUpdating(true);
                } else {
                    Log.d(TAG, "toggleUpdates: Timer Not Paused");

                    mService.pauseTimer();
                    mViewModel.setIsTimerUpdating(false);
                }
            }
        }
    }

    private void setObservers() {
        Log.d(TAG, "setObservers: starts");

        mViewModel.getBinder().observe(this, new Observer<TimerService.TimerBinder>() {
            @Override
            public void onChanged(@Nullable TimerService.TimerBinder mBinder) {
                if (mBinder == null) {
                    Log.d(TAG, "onChanged: unbound from service");
                } else {
                    Log.d(TAG, "onChanged: bound to service.");
                    mService = mBinder.getService();
                }
            }
        });

        mViewModel.getIsTimerUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean aBoolean) {
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (mViewModel.getIsTimerUpdating().getValue()) {
                            if (mViewModel.getBinder().getValue() != null) { // service is bound
                                if (mService.getProgress() == mService.getStopValue()) {
                                    mViewModel.setIsTimerUpdating(false);
                                }
                                // Update UI countdown while timer value > 0
                                String progressMinutes = String.format("%02d", mService.getProgress() / 60);
                                String progressSeconds = String.format("%02d", mService.getProgress() % 60);

                                mTextView.setText(progressMinutes + ":" + progressSeconds);
                            }
                            handler.postDelayed(this, 100);
                        } else {
                            handler.removeCallbacks(this);
                        }
                    }
                };

                // control what the button shows
                if (aBoolean) {
                    mButton.setText("Pause");
                    handler.postDelayed(runnable, 100);

                } else {
                    if (mService.getProgress() == mService.getStopValue()) {
                        mButton.setText("Restart");
                    } else {
                        mButton.setText("Start");
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: starts");
        super.onResume();
        startService();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: starts");
        super.onStop();
        if (mViewModel.getBinder() != null) {
            unbindService(mViewModel.getServiceConnection());
        }
    }

    private void startService() {
        Log.d(TAG, "startService: starts");
        Intent serviceIntent = new Intent(this, TimerService.class);
        startService(serviceIntent);

        bindService();
    }

    private void bindService() {
        Log.d(TAG, "bindService: starts");
        Intent serviceBindIntent = new Intent(this, TimerService.class);
        bindService(serviceBindIntent, mViewModel.getServiceConnection(), Context.BIND_AUTO_CREATE);
    }
}
