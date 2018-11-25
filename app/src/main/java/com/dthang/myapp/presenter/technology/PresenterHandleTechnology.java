package com.dthang.myapp.presenter.technology;

import android.util.Log;

import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.objectclass.TradeMark;
import com.dthang.myapp.model.technology.Technology;
import com.dthang.myapp.view.home.ViewHandleTechnology;

import java.util.ArrayList;
import java.util.List;

public class PresenterHandleTechnology implements IPresenterHandleTechnology {

    private static final String TAG = "PresenterHandleTechnolo";

    private Technology mTechnology;
    private ViewHandleTechnology viewHandleTechnology;
    private List<com.dthang.myapp.model.objectclass.Technology> listTechnology;

    public PresenterHandleTechnology(ViewHandleTechnology viewHandleTechnology) {
        this.viewHandleTechnology = viewHandleTechnology;
        mTechnology = new Technology();
        initData();
    }

    private void initData() {
        listTechnology =
                new ArrayList<>();

        List<TradeMark> topThuongHieu = mTechnology.getListTrademark("getTrademark");
        List<Product> topDienThoai = mTechnology.getListTop10Product("getTopSmartPhoneAndTablet");


        listTechnology.add(new com.dthang.myapp.model.objectclass.Technology("Top thương hiệu lớn", "Top bán chạy nhất",
                topThuongHieu, topDienThoai));

        List<TradeMark> topPhuKien = mTechnology.getListTrademark("getListAccessories");
        List<Product> topPhuKienBanChay = mTechnology.getListTop10Product("getListTopAccessories");

        listTechnology.add(new com.dthang.myapp.model.objectclass.Technology("Phụ kiện", "Top phụ kiện bán chạy nhất",
                topPhuKien, topPhuKienBanChay));

        List<TradeMark> topTienIch = mTechnology.getListTrademark("getListUtilities");
        List<Product> topSanPhamTienIch = mTechnology.getListTop10Product("getListTopUtilities");


        listTechnology.add(new com.dthang.myapp.model.objectclass.Technology("Tiện ích", "Top bán chạy nhất",
                topTienIch, topSanPhamTienIch));

        Log.e(TAG, "handle: " + topThuongHieu.size() + "___" + topPhuKien.size() + "___" + topTienIch.size());

        Log.e(TAG, "handle: " + topDienThoai.size() + "___" + topPhuKienBanChay.size() + "___" + topSanPhamTienIch.size());
    }

    @Override
    public void handle() {
        viewHandleTechnology.showTechnology(listTechnology, mTechnology.getListLogoTrademark(), mTechnology.getListTop10Product("getListProductNew"));
    }
}
