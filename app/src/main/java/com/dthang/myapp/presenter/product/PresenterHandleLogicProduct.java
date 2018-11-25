package com.dthang.myapp.presenter.product;

import android.view.View;
import android.widget.ProgressBar;

import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.product.MoldeProduct;
import com.dthang.myapp.view.product.ViewHandleProductActivity;

import java.util.List;

public class PresenterHandleLogicProduct implements IPresenterHandleLogicProduct {

    private ViewHandleProductActivity mViewHandleProductActivity;
    private MoldeProduct mMoldeProduct;

    public PresenterHandleLogicProduct(ViewHandleProductActivity mViewHandleProductActivity) {
        this.mViewHandleProductActivity = mViewHandleProductActivity;
        mMoldeProduct = new MoldeProduct();
    }

    @Override
    public void handle(boolean isProductType, int id) {
        List<Product> products;
        if (isProductType) {
            products = mMoldeProduct.getListProduct("getProductFromProductType", id, 0);
        } else {
            products = mMoldeProduct.getListProduct("getProductFromTrademark", id, 0);
        }

        mViewHandleProductActivity.showProductList(products);

    }

    public List<Product> getListLoadMore(boolean isProductType, int id, ProgressBar progressBar, int limit) {
        progressBar.setVisibility(View.VISIBLE);
        List<Product> products;
        if (isProductType) {
            products = mMoldeProduct.getListProduct("getProductFromProductType", id, limit);
        } else {
            products = mMoldeProduct.getListProduct("getProductFromTrademark", id, limit);
        }

        progressBar.setVisibility(View.VISIBLE);
        return products;
    }

}
