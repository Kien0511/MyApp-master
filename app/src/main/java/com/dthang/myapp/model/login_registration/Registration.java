package com.dthang.myapp.model.login_registration;

import com.dthang.myapp.handleservice.DownloadJSON;
import com.dthang.myapp.model.objectclass.Employee;
import com.dthang.myapp.util.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Registration {

    private static final String TAG = "Registration";

    private DownloadJSON mDownloadJSON;


    public String registration(Employee employee) {
        String result = "";


        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hashMapRegistration = new HashMap<>();
        hashMapRegistration.put("handle", "registration");

        HashMap<String, String> hashMapEMPLOYEE_NAME = new HashMap<>();
        hashMapEMPLOYEE_NAME.put("EMPLOYEE_NAME", employee.getEmployeeName());

        HashMap<String, String> hashMapEMPLOYEE_USER_NAME = new HashMap<>();
        hashMapEMPLOYEE_USER_NAME.put("EMPLOYEE_USER_NAME", employee.getEmployeeUserName());

        HashMap<String, String> hashMapEMPLOYEE_PASSWORD = new HashMap<>();
        hashMapEMPLOYEE_PASSWORD.put("EMPLOYEE_PASSWORD", employee.getEmployeePassword());

        HashMap<String, String> hashMapEMPLOYEE_TYPE_ID = new HashMap<>();
        hashMapEMPLOYEE_TYPE_ID.put("EMPLOYEE_TYPE_ID", String.valueOf(employee.getEmployeeTypeID()));

        HashMap<String, String> hashMapEMPLOYEE_SENDEMAIL = new HashMap<>();
        hashMapEMPLOYEE_SENDEMAIL.put("EMPLOYEE_SENDEMAIL", employee.getEmployeeSendEmail());

        attrs.add(hashMapRegistration);
        attrs.add(hashMapEMPLOYEE_NAME);
        attrs.add(hashMapEMPLOYEE_USER_NAME);
        attrs.add(hashMapEMPLOYEE_PASSWORD);
        attrs.add(hashMapEMPLOYEE_TYPE_ID);
        attrs.add(hashMapEMPLOYEE_SENDEMAIL);


        mDownloadJSON = new DownloadJSON(App._URL, attrs);
        mDownloadJSON.execute();

        try {
            String json = mDownloadJSON.get();

            JSONObject jsonObject = new JSONObject(json);

            if (jsonObject.get("result").equals("true")) {
                result = "true";
            } else result = "false";

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

}
