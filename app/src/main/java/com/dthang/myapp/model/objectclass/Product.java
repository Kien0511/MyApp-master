package com.dthang.myapp.model.objectclass;

import java.util.List;

public class Product {
    public Product() {
    }

    private int PRODUCT_ID,PRODUCT_PRICE,PRODUCT_AMOUNT,PRODUCT_TYPE_ID,TRADEMARK_ID,EMPLOYEE_ID,PRODUCT_LUOTMUA;
    private String PRODUCT_NAME,PRODUCT_BIG_IMAGE,PRODUCT_SMALL_IMAGE,PRODUCT_INFOMATION;
    private String EMPLOYEE_NAME;

    public String getEMPLOYEE_NAME() {
        return EMPLOYEE_NAME;
    }

    public void setEMPLOYEE_NAME(String EMPLOYEE_NAME) {
        this.EMPLOYEE_NAME = EMPLOYEE_NAME;
    }

    List<ProductDetail> productDetailList;

    public List<ProductDetail> getProductDetailList() {
        return productDetailList;
    }

    public void setProductDetailList(List<ProductDetail> productDetailList) {
        this.productDetailList = productDetailList;
    }

    public Product(int PRODUCT_ID, int PRODUCT_PRICE, String PRODUCT_NAME, String PRODUCT_BIG_IMAGE) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT_PRICE = PRODUCT_PRICE;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCT_BIG_IMAGE = PRODUCT_BIG_IMAGE;
    }

    public Product(int PRODUCT_ID, int PRODUCT_PRICE, int PRODUCT_AMOUNT, int PRODUCT_TYPE_ID, int TRADEMARK_ID, int EMPLOYEE_ID, int PRODUCT_LUOTMUA, String PRODUCT_NAME,
                   String PRODUCT_BIG_IMAGE, String PRODUCT_SMALL_IMAGE, String PRODUCT_INFOMATION) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT_PRICE = PRODUCT_PRICE;
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
        this.PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
        this.TRADEMARK_ID = TRADEMARK_ID;
        this.EMPLOYEE_ID = EMPLOYEE_ID;
        this.PRODUCT_LUOTMUA = PRODUCT_LUOTMUA;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCT_BIG_IMAGE = PRODUCT_BIG_IMAGE;
        this.PRODUCT_SMALL_IMAGE = PRODUCT_SMALL_IMAGE;
        this.PRODUCT_INFOMATION = PRODUCT_INFOMATION;
    }

    public int getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(int PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public int getPRODUCT_PRICE() {
        return PRODUCT_PRICE;
    }

    public void setPRODUCT_PRICE(int PRODUCT_PRICE) {
        this.PRODUCT_PRICE = PRODUCT_PRICE;
    }

    public int getPRODUCT_AMOUNT() {
        return PRODUCT_AMOUNT;
    }

    public void setPRODUCT_AMOUNT(int PRODUCT_AMOUNT) {
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
    }

    public int getPRODUCT_TYPE_ID() {
        return PRODUCT_TYPE_ID;
    }

    public void setPRODUCT_TYPE_ID(int PRODUCT_TYPE_ID) {
        this.PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    }

    public int getTRADEMARK_ID() {
        return TRADEMARK_ID;
    }

    public void setTRADEMARK_ID(int TRADEMARK_ID) {
        this.TRADEMARK_ID = TRADEMARK_ID;
    }

    public int getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    public void setEMPLOYEE_ID(int EMPLOYEE_ID) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    public int getPRODUCT_LUOTMUA() {
        return PRODUCT_LUOTMUA;
    }

    public void setPRODUCT_LUOTMUA(int PRODUCT_LUOTMUA) {
        this.PRODUCT_LUOTMUA = PRODUCT_LUOTMUA;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCT_BIG_IMAGE() {
        return PRODUCT_BIG_IMAGE;
    }

    public void setPRODUCT_BIG_IMAGE(String PRODUCT_BIG_IMAGE) {
        this.PRODUCT_BIG_IMAGE = PRODUCT_BIG_IMAGE;
    }

    public String getPRODUCT_SMALL_IMAGE() {
        return PRODUCT_SMALL_IMAGE;
    }

    public void setPRODUCT_SMALL_IMAGE(String PRODUCT_SMALL_IMAGE) {
        this.PRODUCT_SMALL_IMAGE = PRODUCT_SMALL_IMAGE;
    }

    public String getPRODUCT_INFOMATION() {
        return PRODUCT_INFOMATION;
    }

    public void setPRODUCT_INFOMATION(String PRODUCT_INFOMATION) {
        this.PRODUCT_INFOMATION = PRODUCT_INFOMATION;
    }
}
