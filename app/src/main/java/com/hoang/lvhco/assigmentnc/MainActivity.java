package com.hoang.lvhco.assigmentnc;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.hoang.lvhco.assigmentnc.fragment.CourseFragment;
import com.hoang.lvhco.assigmentnc.fragment.MapsFragment;
import com.hoang.lvhco.assigmentnc.fragment.NewFragment;
import com.hoang.lvhco.assigmentnc.fragment.SocialFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_course:
                    selectedFragment = new CourseFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, selectedFragment).commit();
                    setTitle("Course");
                    return true;
                case R.id.navigation_map:
                    selectedFragment = new MapsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, selectedFragment).commit();
                    setTitle("Maps");
                    return true;
                case R.id.navigation_new:
                    selectedFragment = new NewFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, selectedFragment).commit();
                    setTitle("News");
                    return true;
                case R.id.navigation_social:
                    selectedFragment = new SocialFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, selectedFragment).commit();
                    setTitle("Social");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
