package com.dthang.myapp.view.product_detail;

import com.dthang.myapp.model.objectclass.Product;

public interface IViewProductDetail {
    void ShowProductDetail(Product product);
    void ShowProductSlider(String[] linkProductImage);
}
