package com.example.framgiahangoclong.demoservice.Fragment;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.framgiahangoclong.demoservice.DemoService;
import com.example.framgiahangoclong.demoservice.OnDataChangeListener;
import com.example.framgiahangoclong.demoservice.OnHahahaa;
import com.example.framgiahangoclong.demoservice.R;

public class FirstFragment extends Fragment implements View.OnClickListener, OnDataChangeListener{
    private DemoService mService;
    private Button mButtonFillData;
    private TextView mTextView;
    private boolean mIsBound;
    private OnHahahaa mHahahaa;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mTextView = view.findViewById(R.id.text_view);
        mButtonFillData = view.findViewById(R.id.button_fillData);
        mButtonFillData.setOnClickListener(this);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DemoService.LocalBinder binder = (DemoService.LocalBinder) service;
            mService = binder.getService();
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if (!mIsBound) {
            Intent intent = new Intent(getContext(), DemoService.class);
            getActivity().bindService(intent, mServiceConnection, getActivity().BIND_AUTO_CREATE);
            Toast.makeText(getContext(), "Service is started", Toast.LENGTH_SHORT).show();
        }
        Log.d("hahaha", "onStart First Fragment: ");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_fillData:
                if (mIsBound) {
                    mService.setListener(this);
                    mService.passData();
                }
                break;
        }
    }

    @Override
    public void onStop() {
        mService.stopPassData();
        getActivity().unbindService(mServiceConnection);
        Log.d("hahaha", "onStop First Fragment: ");
        super.onStop();
    }

    @Override
    public void OnDataChanged(int i) {
        mTextView.setText(String.valueOf(i));
        mHahahaa.hhaha(i);
        Log.d("haha", "First Fragment: " + i);

    }
}
