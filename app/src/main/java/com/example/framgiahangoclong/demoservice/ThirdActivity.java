package com.example.framgiahangoclong.demoservice;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.framgiahangoclong.demoservice.Fragment.FirstFragment;
import com.example.framgiahangoclong.demoservice.Fragment.SecondFragment;

public class ThirdActivity extends AppCompatActivity{

    private FragmentManager mFragmentManager;
    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mFragmentManager =  getSupportFragmentManager();
        FragmentTransaction firstFragmentTransaction = mFragmentManager.beginTransaction();
        FragmentTransaction secondFragmentTransaction = mFragmentManager.beginTransaction();

        mFirstFragment  = new FirstFragment();
        mSecondFragment = new SecondFragment();

        firstFragmentTransaction.add(R.id.container, mFirstFragment);
        secondFragmentTransaction.add(R.id.container, mSecondFragment);

        firstFragmentTransaction.commit();
        secondFragmentTransaction.commit();
    }


    public void hhaha(int i) {
        mSecondFragment.updateData(i);
    }
}
