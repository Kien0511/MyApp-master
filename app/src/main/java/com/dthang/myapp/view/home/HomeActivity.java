package com.dthang.myapp.view.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.dthang.myapp.R;
import com.dthang.myapp.adapter.HomeExpandAdapter;
import com.dthang.myapp.adapter.HomeViewPagerAdapter;
import com.dthang.myapp.model.objectclass.ProductType;
import com.dthang.myapp.presenter.home.PresenterLogicHandleMenu;
import com.dthang.myapp.view.login_registration.LoginActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ViewHandleMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = "HomeActivity";

    //
    private FragmentManager mFragmentManager;

    private ExpandableListView ep_menu;
    private Toolbar toolbar_home;
    private TabLayout tabLayout_home;
    private ViewPager viewPager_home;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MenuItem it_logout, it_login, it_search;
    private Menu mMenu;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;

    //
    private PresenterLogicHandleMenu mPresenterLogicHandleMenu;

    private AccessToken mAccessToken;
    private GoogleSignInAccount mGoogleSignInAccount;
    private GoogleApiClient mGoogleApiClient;
    private String userName = "";

    private boolean isHideSearch = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_home);
        initView();
        handleView();
        initGoogleApiClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        handleMenus();
    }



//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.e(TAG, "onPause: ");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.e(TAG, "onResume: ");
//    }

    private void handleMenus() {
        mPresenterLogicHandleMenu = new PresenterLogicHandleMenu(this, this);
        mPresenterLogicHandleMenu.getMenus();
        mAccessToken = mPresenterLogicHandleMenu.getmAccessToken(this);
        mGoogleSignInAccount = mPresenterLogicHandleMenu.getGoogleSignInAccount(this);
        mPresenterLogicHandleMenu.handle(this);
        userName = mPresenterLogicHandleMenu.getUserName();
        if (mMenu != null){
            mMenu.clear();
            onCreateOptionsMenu(mMenu);
        }
    }

    private void handleView() {
        setSupportActionBar(toolbar_home);
        getSupportActionBar().setTitle("");

        //handle tablayout and viewpager in home activity
        HomeViewPagerAdapter mHomeViewPagerAdapter = new HomeViewPagerAdapter(mFragmentManager);
        viewPager_home.setAdapter(mHomeViewPagerAdapter);
        tabLayout_home.setupWithViewPager(viewPager_home);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBarDrawerToggle.syncState();

        mAppBarLayout.addOnOffsetChangedListener(this);

    }

    private void initView() {
        mFragmentManager = getSupportFragmentManager();

        toolbar_home = findViewById(R.id.toolbar_main);
        tabLayout_home = findViewById(R.id.tablayout_home);
        viewPager_home = findViewById(R.id.viewpager_home);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        ep_menu = findViewById(R.id.ep_menu);
        mCollapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        mAppBarLayout = findViewById(R.id.appbar_layout);
    }

    private void setFullScreen() {
        Window window = getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(winParams);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        mMenu = menu;
        it_logout = menu.findItem(R.id.it_loguot);
        it_login = menu.findItem(R.id.it_login);
        it_search = menu.findItem(R.id.it_search);

        if (isHideSearch) {
            it_search.setVisible(false);
        } else it_search.setVisible(true);

        if (mGoogleSignInAccount != null) {
            it_login.setTitle(mGoogleSignInAccount.getEmail());
            it_logout.setVisible(true);
        }

        if (!userName.equals("")) {
            it_login.setTitle(userName);
            it_logout.setVisible(true);
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.it_login: {
                if (mAccessToken == null && mGoogleSignInAccount == null && userName.equals("")) {
                    handleLogin();
                }
                break;
            }
            case R.id.it_loguot: {
                handleLogout();
                break;
            }
            case R.id.it_noti: {
            }
        }

        return super.onOptionsItemSelected(item);
    }


    private void handleLogin() {
        Intent iLogin = new Intent(this, LoginActivity.class);
        startActivity(iLogin);
    }

    private void handleLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Bạn có chắc chắn muốn đăng xuất!")
                .setTitle("Cảnh báo")
                .setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAccessToken != null) {
                    LoginManager.getInstance().logOut();
                    mAccessToken = mPresenterLogicHandleMenu.getmAccessToken(HomeActivity.this);
                    mMenu.clear();
                    onCreateOptionsMenu(mMenu);
                } else if (mGoogleSignInAccount != null) {
                    signOut();
                    mGoogleSignInAccount = mPresenterLogicHandleMenu.getGoogleSignInAccount(HomeActivity.this);
                    mMenu.clear();
                    onCreateOptionsMenu(mMenu);
                }else if (!userName.equals("")){
                    mPresenterLogicHandleMenu.handleLogout();
                    userName = mPresenterLogicHandleMenu.getUserName();
                    mMenu.clear();
                    onCreateOptionsMenu(mMenu);
                }
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }

    private void initGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void showMenus(List<ProductType> productTypes) {
        HomeExpandAdapter homeExpandAdapter = new HomeExpandAdapter(this, productTypes);
        ep_menu.setAdapter(homeExpandAdapter);
        homeExpandAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleMenuLoginAndLogout(AccessToken accessToken, GoogleSignInAccount googleSignInAccount) {
        mAccessToken = accessToken;
        mGoogleSignInAccount = googleSignInAccount;

        if (accessToken != null) {
            setUserName(accessToken);
        } else if (googleSignInAccount != null) {
        }
    }


    private void setUserName(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    Log.e(TAG, "onCompleted: ");
                    String userName = object.getString("name");
                    it_login.setTitle(userName);
                    it_logout.setVisible(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "name");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private boolean a = true;
    private boolean b = true;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        LinearLayout search_Layout = mAppBarLayout.findViewById(R.id.search_layout);
        int toolBarHeight = toolbar_home.getMeasuredHeight();
        int appBarHeight = appBarLayout.getMeasuredHeight();
        Float f = ((((float) appBarHeight - toolBarHeight) + verticalOffset) / ((float) appBarHeight - toolBarHeight)) * 255;

        Log.d(TAG, "onOffsetChanged: "+ appBarHeight + "--" +toolBarHeight +"--"+verticalOffset);

        search_Layout.setAlpha(verticalOffset + 10);



//        if (verticalOffset == -161) {
//            if (a) {
//                isHideSearch = false;
//                invalidateOptionsMenu();
//                b = false;
//            }
//
//        } else if (verticalOffset == 0) {
//            if (mMenu != null) {
//                if (b) {
//                    isHideSearch = true;
//                    invalidateOptionsMenu();
//                    a = false;
//                }
//            }
//        } else {
//            a = true;
//            b = true;
//        }


    }
}
