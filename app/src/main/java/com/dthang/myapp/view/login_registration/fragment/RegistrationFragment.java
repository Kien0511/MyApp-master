package com.dthang.myapp.view.login_registration.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dthang.myapp.R;
import com.dthang.myapp.customview.EditTextPassWord;
import com.dthang.myapp.customview.EditTextUserName;
import com.dthang.myapp.model.objectclass.Employee;
import com.dthang.myapp.presenter.login_registration.PresenterHandleLogicRegistration;
import com.dthang.myapp.view.home.HomeActivity;
import com.dthang.myapp.view.login_registration.ViewHandleRegistration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFragment extends Fragment implements View.OnFocusChangeListener, View.OnClickListener, ViewHandleRegistration {

    private static final String TAG = "RegistrationFragment";

    //
    private static final String PATTERN_NAME = "([a-zA-Z\\s].{6,})";
    private static final String PATTERN_PASSWORD = "((?=.*[\\d])(?=.*[a-z])(?=.*[A-Z]).{6,50})";

    //View
    private TextInputLayout til_name, til_email, til_password, til_retrypassword;
    private EditTextUserName edt_name, edt_email;
    private EditTextPassWord edt_password, edt_retrypassword;
    private SwitchCompat switchCompat_SendEmail;
    private Button bt_registration, bt_registration_facebook, bt_registration_google;
    private TextView tv_rules;

    //
    private boolean isInputOK = false;
    private boolean isSendMail = false;

    //
    private PresenterHandleLogicRegistration mPresenterHandleLogicRegistration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterHandleLogicRegistration = new PresenterHandleLogicRegistration(this,getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        til_name = view.findViewById(R.id.til_name);
        til_email = view.findViewById(R.id.til_email);
        til_password = view.findViewById(R.id.til_password);
        til_retrypassword = view.findViewById(R.id.til_retrypassword);

        edt_name = view.findViewById(R.id.edt_name);
        edt_email = view.findViewById(R.id.edt_email);
        edt_password = view.findViewById(R.id.edt_registration_password);
        edt_retrypassword = view.findViewById(R.id.edt_registration_retrypassword);

        bt_registration = view.findViewById(R.id.bt_registration);
        bt_registration_facebook = view.findViewById(R.id.bt_registration_withfacebook);
        bt_registration_google = view.findViewById(R.id.bt_registration_withgoogle);

        switchCompat_SendEmail = view.findViewById(R.id.swichcompat_sendemail);

        tv_rules = view.findViewById(R.id.tv_rules);

        edt_name.setOnFocusChangeListener(this);
        edt_email.setOnFocusChangeListener(this);
        edt_password.setOnFocusChangeListener(this);
        edt_retrypassword.setOnFocusChangeListener(this);

        bt_registration.setOnClickListener(this);

        switchCompat_SendEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSendMail = isChecked;
            }
        });

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {
            case R.id.edt_name: {
                if (!hasFocus) {
                    Pattern pattern_name = Pattern.compile(PATTERN_NAME);
                    handleInput(til_name, pattern_name, edt_name.getText().toString(), "Tên nhập không đúng!");
                }
                break;
            }
            case R.id.edt_email: {
                if (!hasFocus) {
                    handleInput(til_email, Patterns.EMAIL_ADDRESS, edt_email.getText().toString(), "Địa chỉ Email không đúng!");
                }
                break;
            }
            case R.id.edt_registration_password: {
                if (!hasFocus) {
                    Pattern pattern_password = Pattern.compile(PATTERN_PASSWORD);
                    handleInput(til_password, pattern_password, edt_password.getText().toString(), "Mật khẩu phải trên 6 ký tự và có chũ hoa, chữ thường!");
                }
                break;
            }
            case R.id.edt_registration_retrypassword: {
                if (!hasFocus) {
                    if (!edt_password.getText().toString().equals(edt_retrypassword.getEditableText().toString())) {
                        isInputOK = false;
                        til_retrypassword.setErrorEnabled(true);
                        til_retrypassword.setError("Nhập lại mật khẩu không đúng!");
                    } else {
                        isInputOK = true;
                        til_retrypassword.setErrorEnabled(false);
                        til_retrypassword.setError("");
                    }
                }
                break;
            }
        }
    }

    private void handleInput(TextInputLayout textInputLayout, Pattern pattern, String input, String error) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            isInputOK = false;
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(error);
        } else {
            isInputOK = true;
            textInputLayout.setErrorEnabled(false);
            textInputLayout.setError("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_registration: {
                if (!edt_password.getText().toString().equals(edt_retrypassword.getEditableText().toString())) {
                    isInputOK = false;
                    til_retrypassword.setErrorEnabled(true);
                    til_retrypassword.setError("Nhập lại mật khẩu không đúng!");
                } else {
                    isInputOK = true;
                    til_retrypassword.setErrorEnabled(false);
                    til_retrypassword.setError("");
                }
                onClickRegistration();
                break;
            }
        }
    }

    private void onClickRegistration() {
        if (isInputOK && !edt_retrypassword.getText().toString().equals("") && !edt_password.getText().toString().equals("")
                && !edt_name.getText().toString().equals("") && !edt_email.getText().toString().equals("")) {
            Employee employee = new Employee(2, edt_name.getText().toString(), edt_email.getText().toString()
                    , edt_password.getText().toString(), isSendMail + "");
            mPresenterHandleLogicRegistration.handleRegistration(employee);
        }else {
            Toast.makeText(getActivity(), "Đăng ký thất bại! Thử lại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleRegistration() {
        getActivity().finish();
        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }
}
