package com.dthang.myapp.view.login_registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dthang.myapp.R;
import com.dthang.myapp.adapter.LoginViewpagerAdapter;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar_login;
    private TabLayout tabLayout_login;
    private ViewPager viewPager_login;
    private LoginViewpagerAdapter mLoginViewpagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        init();
    }

    private void initView() {
        toolbar_login = findViewById(R.id.toolbar_login);
        tabLayout_login = findViewById(R.id.tablayout_login);
        viewPager_login= findViewById(R.id.viewpager_login);
    }

    private void init() {
        mLoginViewpagerAdapter = new LoginViewpagerAdapter(getSupportFragmentManager());
        setSupportActionBar(toolbar_login);
        getSupportActionBar().setTitle("Đăng nhập/Đăng ký");
        viewPager_login.setAdapter(mLoginViewpagerAdapter);
        tabLayout_login.setupWithViewPager(viewPager_login);
        mLoginViewpagerAdapter.notifyDataSetChanged();
    }
}
