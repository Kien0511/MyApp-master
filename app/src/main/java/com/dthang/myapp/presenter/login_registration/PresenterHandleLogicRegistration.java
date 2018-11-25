package com.dthang.myapp.presenter.login_registration;

import android.content.Context;
import android.content.SharedPreferences;

import com.dthang.myapp.model.login_registration.Registration;
import com.dthang.myapp.model.objectclass.Employee;
import com.dthang.myapp.util.App;
import com.dthang.myapp.view.login_registration.ViewHandleRegistration;

public class PresenterHandleLogicRegistration implements IPresenterHandleLogicRegistration {

    private ViewHandleRegistration mViewHandleRegistration;
    private Registration mRegistration;
    private SharedPreferences dataLogin;
    private Context mContext;
    private SharedPreferences.Editor mEditor;

    public PresenterHandleLogicRegistration(ViewHandleRegistration mViewHandleRegistration, Context context) {
        this.mViewHandleRegistration = mViewHandleRegistration;
        mContext = context;
        mRegistration = new Registration();
    }

    @Override
    public void handleRegistration(Employee employee) {
        String result = mRegistration.registration(employee);
        if (result.equals("true")) {
            mViewHandleRegistration.handleRegistration();
            dataLogin = mContext.getSharedPreferences(App.NAME_SHAREDPREFERENCES, Context.MODE_PRIVATE);
            mEditor = dataLogin.edit();
            mEditor.putString("username", employee.getEmployeeUserName());
            mEditor.commit();
        }
    }
}
