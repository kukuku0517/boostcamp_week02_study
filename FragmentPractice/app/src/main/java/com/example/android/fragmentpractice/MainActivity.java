package com.example.android.fragmentpractice;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SecondFragment.OnFragmentInteractionListener {
private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        Fragment f1 = new FirstFragment();
//        Fragment f2 = new SecondFragment();
//        ft.add(R.id.firstContainer,f1);
//        ft.add(R.id.secondContainer,f2);
//        ft.commit();
//        tv= (TextView) findViewById(R.id.textView);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment() ,null)
                .commit();


    }

    @Override
    public void onFragmentInteraction(String s) {
        tv.setText(s);
    }
}
