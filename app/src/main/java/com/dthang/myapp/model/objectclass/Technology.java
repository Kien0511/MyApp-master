package com.dthang.myapp.model.objectclass;

import java.util.List;

public class Technology {
    private String title1,title2;
    private List<TradeMark> mListTrademark;
    private List<Product> mListProduct;

    public Technology(String title1, String title2, List<TradeMark> mListTrademark, List<Product> mListProduct) {
        this.title1 = title1;
        this.title2 = title2;
        this.mListTrademark = mListTrademark;
        this.mListProduct = mListProduct;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public List<TradeMark> getmListTrademark() {
        return mListTrademark;
    }

    public void setmListTrademark(List<TradeMark> mListTrademark) {
        this.mListTrademark = mListTrademark;
    }

    public List<Product> getmListProduct() {
        return mListProduct;
    }

    public void setmListProduct(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }
}
