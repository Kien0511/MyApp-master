package com.dthang.myapp.model.objectclass;

import java.util.List;

public class ProductType {
    private int productTypeID,productTypeParentID;
    private String productTypeName;
    private List<ProductType> listChidel;

    public ProductType(int productTypeID, int productTypeParentID, String productTypeName) {
        this.productTypeID = productTypeID;
        this.productTypeParentID = productTypeParentID;
        this.productTypeName = productTypeName;
    }

    public int getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(int productTypeID) {
        this.productTypeID = productTypeID;
    }

    public int getProductTypeParentID() {
        return productTypeParentID;
    }

    public void setProductTypeParentID(int productTypeParentID) {
        this.productTypeParentID = productTypeParentID;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public List<ProductType> getListChidel() {
        return listChidel;
    }

    public void setListChidel(List<ProductType> listChidel) {
        this.listChidel = listChidel;
    }
}
