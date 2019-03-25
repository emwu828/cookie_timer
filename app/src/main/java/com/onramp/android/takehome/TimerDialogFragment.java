package com.onramp.android.takehome;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class TimerDialogFragment extends DialogFragment {
    private static final String TAG = "TimerDialogFragment";

    // UI Components
    private EditText mInput;
    private Button mActionOk, mActionCancel;

    // Vars
    public OnInputListener mOnInputListener;

    // Interface
    public interface OnInputListener {
        void sendInput(Integer input);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");

        // Inflate UI components
        View view = inflater.inflate(R.layout.fragment_timer_dialog, container, false);
        mActionCancel = view.findViewById(R.id.button_cancel);
        mActionOk = view.findViewById(R.id.button_ok);
        mInput = view.findViewById(R.id.timer_input);

        // Set clickListeners
        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");

                // Send user input value to TimerActivity(OnInputListener)
                String input = mInput.getText().toString();
                mOnInputListener.sendInput(Integer.valueOf(input));

                getDialog().dismiss();
            }
        });

        //return the view
        return view;
    }

    // Attach fragment to the activity
    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: starts");

        super.onAttach(context);
        try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
        }
    }
}
