package com.dthang.myapp.presenter.home;

import android.content.Context;
import android.content.SharedPreferences;

import com.dthang.myapp.handleservice.DownloadJSON;
import com.dthang.myapp.model.home.DataHome;
import com.dthang.myapp.model.login_registration.Login;
import com.dthang.myapp.model.objectclass.ProductType;
import com.dthang.myapp.util.App;
import com.dthang.myapp.view.home.ViewHandleMenu;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicHandleMenu implements IPresenterHandleMenu{

    private Login login;
    private List<HashMap<String,String>> arrts = new ArrayList<>();
    private ViewHandleMenu viewHandleMenu;
    private Context mContext;

    public PresenterLogicHandleMenu(ViewHandleMenu viewHandleMenu,Context context) {
        this.viewHandleMenu = viewHandleMenu;
        mContext = context;
        login = new Login();
    }

    @Override
    public void getMenus() {

        List<ProductType> mListProductTypes = new ArrayList<>();
        String data;
        HashMap<String,String> hashMapMenu = new HashMap<>();
        hashMapMenu.put("handle","getMenus");

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("product_type_parentid","0");
        arrts.add(hashMapMenu);
        arrts.add(hashMap);

        DownloadJSON downloadJSON = new DownloadJSON(App._URL,arrts);
        downloadJSON.execute();

        try {
            data = downloadJSON.get();
            DataHome dataHome = new DataHome();
            mListProductTypes = dataHome.parserJSONProductType(data);
            viewHandleMenu.showMenus(mListProductTypes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(Context context) {

        if (login.getmAccessToken() == null){
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
            viewHandleMenu.handleMenuLoginAndLogout(null,account);
        }else {
            viewHandleMenu.handleMenuLoginAndLogout(login.getmAccessToken(),null);
        }

    }

    @Override
    public AccessToken getmAccessToken(Context context) {
        return login.getmAccessToken();
    }

    @Override
    public GoogleSignInAccount getGoogleSignInAccount(Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        return account;
    }

    @Override
    public String getUserName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(App.NAME_SHAREDPREFERENCES,Context.MODE_PRIVATE);
        return  sharedPreferences.getString("username","");
    }

    @Override
    public void handleLogout() {
        login.updateLogin(mContext,"");
    }

}
