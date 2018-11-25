package com.dthang.myapp.view.login_registration.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dthang.myapp.R;
import com.dthang.myapp.customview.EditTextPassWord;
import com.dthang.myapp.customview.EditTextUserName;
import com.dthang.myapp.presenter.login_registration.PresenterHandleLogicLogin;
import com.dthang.myapp.view.home.HomeActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.util.Arrays;

public class LoginFragment extends Fragment implements View.OnClickListener,ViewHandleLogin {

    private static final String TAG = "LoginFragment";

    public static final String EMAIL = "email";
    public static final int RC_SIGN_IN = 1001;

    private Button bt_Login, bt_Login_Withsms, bt_Login_Withfacebook, bt_Login_Withgoogle;
    private EditTextUserName ed_TextUserName;
    private EditTextPassWord ed_TextPassWord;

    private CallbackManager mCallbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private PresenterHandleLogicLogin mPresenterHandleLogicLogin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCallbackManager = CallbackManager.Factory.create();
        mGoogleSignInClient = getGoogleSignIn(getContext());
        mPresenterHandleLogicLogin = new PresenterHandleLogicLogin(this,getActivity());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        bt_Login = view.findViewById(R.id.bt_login);
        bt_Login_Withfacebook = view.findViewById(R.id.bt_login_withfacebook);
        bt_Login_Withsms = view.findViewById(R.id.bt_login_withsms);
        bt_Login_Withgoogle = view.findViewById(R.id.bt_login_withgoogle);
        ed_TextUserName = view.findViewById(R.id.ed_user);
        ed_TextPassWord = view.findViewById(R.id.ed_password);


        bt_Login_Withfacebook.setOnClickListener(this);
        bt_Login_Withgoogle.setOnClickListener(this);
        bt_Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_login: {
                handleLogin();
                break;
            }
            case R.id.bt_login_withsms: {
                break;
            }
            case R.id.bt_login_withfacebook: {
                handleLoginWithFacebook();
                break;
            }
            case R.id.bt_login_withgoogle: {
                handleLoginWithGoogle();
                break;
            }
            default: {

            }
        }
    }

    private void handleLogin() {
        mPresenterHandleLogicLogin.handleLogin(ed_TextUserName.getText().toString(),ed_TextPassWord.getText().toString());
    }

    private void handleLoginWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public GoogleSignInClient getGoogleSignIn(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        return GoogleSignIn.getClient(context, gso);
    }

    private void handleLoginWithFacebook() {
        Log.e(TAG, "handleLoginWithFacebook: ");
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e(TAG, "Thanh cong");
                        getActivity().finish();
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "Thoat");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.e(TAG, "Loi");
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                getActivity().finish();
            }
        }
    }


    @Override
    public void loginOK() {
        Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(getActivity(), "Tên đăng nhập và mật khẩu sai!", Toast.LENGTH_SHORT).show();
    }
}
