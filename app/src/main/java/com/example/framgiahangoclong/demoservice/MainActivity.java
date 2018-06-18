package com.example.framgiahangoclong.demoservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, OnDataChangeListener{

    private DemoService mService;
    private Button mButtonStartService;
    private Button mButtonNextActivity;
    private TextView mTextView;
    private boolean mIsBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTextView = findViewById(R.id.text_view);
        mButtonStartService = findViewById(R.id.button_start);
        mButtonNextActivity = findViewById(R.id.button_next_activity);
        mButtonStartService.setOnClickListener(this);
        mButtonNextActivity.setOnClickListener(this);
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
    protected void onStart() {
        super.onStart();
        if (!mIsBound) {
            Intent intent = new Intent(MainActivity.this, DemoService.class);
            bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
            Toast.makeText(this, "Service is started", Toast.LENGTH_SHORT).show();
        }
        Log.d("hahaha", "onStart MainActivity: ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                if (mIsBound) {
                    mService.setListener(this);
                    mService.passData();
                }
                break;
            case R.id.button_next_activity:
                startActivity(new Intent(this, SecondActivity.class));
        }
    }

    @Override
    protected void onStop() {
        mService.stopPassData();
        unbindService(mServiceConnection);
        Log.d("hahaha", "onStop MainActivity: ");
        super.onStop();
    }

    @Override
    public void OnDataChanged(int i) {
        mTextView.setText(String.valueOf(i));
        Log.d("haha", "MainActivity: " + i);
    }
}
