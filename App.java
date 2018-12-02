package com.dthang.myapp.util;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static final String _URL = "http://192.168.42.226:81/myapp/query.php";
    public static final String _URL1 = "http://vnchild.com/myapp/query.php";
    public static final String NAME_SHAREDPREFERENCES = "login";
    public static final String LOGTAG = "dulieu";
    public static final String SERVER1 = "http://192.168.1.111:81/myapp";
    public static final String SERVER = "http://vnchild.com/myapp";
}
