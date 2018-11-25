package com.dthang.myapp.view.welcome;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dthang.myapp.R;
import com.dthang.myapp.util.More;
import com.dthang.myapp.view.home.HomeActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WelcomeActivity extends AppCompatActivity {

    private Thread mThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.dthang.myapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("key111:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        More.setFullScreen(getWindow());
        openHomeActivity();
    }

    private void openHomeActivity() {
        if (isNetworkConnected()) {
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent iOpenHomeActivity = new Intent(WelcomeActivity.this, HomeActivity.class);
                        startActivity(iOpenHomeActivity);
                        finish();
                    }
                }
            });
            mThread.start();
        } else {
//            showAlertDialog();
            showSnackbar();
        }
    }

    private void showSnackbar() {
        final Snackbar snackbar = Snackbar.make((FrameLayout) findViewById(R.id.frameLayout), "Không có kết nối", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkConnected()) {
                    openHomeActivity();
                } else {
                   showSnackbar();
                }
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Thông báo!")
                .setMessage("Không có kết nối Internet!");
        builder.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openHomeActivity();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
