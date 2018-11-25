package com.dthang.myapp.presenter.login_registration;

import android.content.Context;

import com.dthang.myapp.model.login_registration.Login;
import com.dthang.myapp.view.login_registration.fragment.ViewHandleLogin;

public class PresenterHandleLogicLogin implements IPresenterHandleLogicLogin {

    private Login mLogin;
    private ViewHandleLogin mViewHandleLogin;
    private Context mContext;

    public PresenterHandleLogicLogin(ViewHandleLogin mViewHandleLogin, Context context) {
        this.mViewHandleLogin = mViewHandleLogin;
        mContext = context;
        mLogin = new Login();
    }

    @Override
    public void handleLogin(String email, String password) {

        String usreName = mLogin.login(email,password);
        if (!usreName.equals("")){
            mViewHandleLogin.loginOK();
            mLogin.updateLogin(mContext,usreName);
        }else mViewHandleLogin.loginError();
    }



}
