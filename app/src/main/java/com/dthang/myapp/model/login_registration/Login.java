package com.dthang.myapp.model.login_registration;

import android.content.Context;
import android.content.SharedPreferences;

import com.dthang.myapp.handleservice.DownloadJSON;
import com.dthang.myapp.util.App;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Login {
    private AccessToken mAccessToken = null;
    private AccessTokenTracker mAccessTokenTracker;

    public AccessToken getmAccessToken() {

        mAccessToken = AccessToken.getCurrentAccessToken();

        if (mAccessToken == null ){
            mAccessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    mAccessToken = currentAccessToken;
                    closeAccessTokenTracker();
                }
            };
            mAccessTokenTracker.startTracking();
        }

        return mAccessToken;
    }

    public void closeAccessTokenTracker(){
        mAccessTokenTracker.stopTracking();
    }

    public String login(String email, String password){
        String userName = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hashMapLogin = new HashMap<>();
        hashMapLogin.put("handle","login");
        HashMap<String,String> hashMapEmail = new HashMap<>();
        hashMapEmail.put("EMPLOYEE_USER_NAME",email);
        HashMap<String,String> hashMapPassword = new HashMap<>();
        hashMapPassword.put("EMPLOYEE_PASSWORD",password);

        attrs.add(hashMapLogin);
        attrs.add(hashMapEmail);
        attrs.add(hashMapPassword);

        DownloadJSON downloadJSON = new DownloadJSON(App._URL,attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject js = new JSONObject(data);
            userName = js.getString("username");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userName;
    }

    public void updateLogin(Context context,String username){
        SharedPreferences sharedPreferences = context.getSharedPreferences(App.NAME_SHAREDPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.commit();
    }
}
