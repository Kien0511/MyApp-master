package com.dthang.myapp.view.home;

import com.dthang.myapp.model.objectclass.ProductType;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

public interface ViewHandleMenu {
    void showMenus(List<ProductType> productTypes);
    void handleMenuLoginAndLogout(AccessToken accessToken,GoogleSignInAccount googleSignInAccount);
}
