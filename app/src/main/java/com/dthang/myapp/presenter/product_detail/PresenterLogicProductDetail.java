package com.dthang.myapp.presenter.product_detail;

import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.product_detail.ModelProductDetail;
import com.dthang.myapp.view.product_detail.IViewProductDetail;

public class PresenterLogicProductDetail implements IPresenterProductDetail{

    IViewProductDetail viewProductDetail;
    ModelProductDetail modelProductDetail;

    public PresenterLogicProductDetail(IViewProductDetail viewProductDetail){
        this.viewProductDetail = viewProductDetail;
        modelProductDetail = new ModelProductDetail();
    }

    @Override
    public void getProductDetail(int product_id) {
        Product product = modelProductDetail.GetProductDetail("getProductAndDetailsProduct","PRODUCTDETAIL",product_id);
        if(product.getPRODUCT_ID() > 0)
        {
            String[] linkProductImage = product.getPRODUCT_SMALL_IMAGE().split(",");
            viewProductDetail.ShowProductSlider(linkProductImage);
            viewProductDetail.ShowProductDetail(product);
        }
    }
}
