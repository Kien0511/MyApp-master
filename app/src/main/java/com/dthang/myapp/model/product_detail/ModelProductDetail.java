package com.dthang.myapp.model.product_detail;

import com.dthang.myapp.handleservice.DownloadJSON;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.objectclass.ProductDetail;
import com.dthang.myapp.util.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelProductDetail {
    public Product GetProductDetail(String function, String array, int product_id){
        Product product = new Product();

        List<ProductDetail> productDetailList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();

        String strURL = App._URL;

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("handle",function);

        HashMap<String,String> hashMapProductId = new HashMap<>();
        hashMapProductId.put("product_id",String.valueOf(product_id));

        attrs.add(hashMap);
        attrs.add(hashMapProductId);

        DownloadJSON downloadJSON = new DownloadJSON(strURL,attrs);

        downloadJSON.execute();

        try {
            String dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);

            JSONArray jsonArrayListProduct = jsonObject.optJSONArray(array);
            int lenght = jsonArrayListProduct.length();

            for (int i = 0; i < lenght; i++)
            {
                JSONObject object = jsonArrayListProduct.optJSONObject(i);
                product.setPRODUCT_ID(object.optInt("PRODUCT_ID"));
                product.setPRODUCT_NAME(object.optString("PRODUCT_NAME"));
                product.setPRODUCT_PRICE(object.optInt("PRODUCT_PRICE"));
                product.setPRODUCT_SMALL_IMAGE(object.optString("PRODUCT_SMALL_IMAGE"));
                product.setPRODUCT_AMOUNT(object.optInt("PRODUCT_AMOUNT"));
                product.setPRODUCT_INFOMATION(object.optString("PRODUCT_INFOMATION"));
                product.setPRODUCT_TYPE_ID(object.optInt("PRODUCT_TYPE_ID"));
                product.setTRADEMARK_ID(object.optInt("TRADEMARK_ID"));
                product.setEMPLOYEE_ID(object.optInt("EMPLOYEE_ID"));
                product.setEMPLOYEE_NAME(object.optString("EMPLOYEE_NAME"));
                product.setPRODUCT_LUOTMUA(object.optInt("PRODUCT_LUOTMUA"));

                JSONArray jsonArrayProductParameter = object.optJSONArray("PRODUCT_PARAMETER");

                for (int j = 0; j < jsonArrayProductParameter.length(); j++)
                {
                    JSONObject jsonObject1 = jsonArrayProductParameter.optJSONObject(j);

                    for (int k = 0; k < jsonObject1.names().length(); k++)
                    {
                        String product_detail_name = jsonObject1.names().getString(k);

                        ProductDetail productDetail = new ProductDetail();

                        productDetail.setPRODUCT_DETAIL_NAME(product_detail_name);
                        productDetail.setPRODUCT_DETAIL_VALUE(jsonObject1.optString(product_detail_name));

                        productDetailList.add(productDetail);
                    }
                }

                product.setProductDetailList(productDetailList);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return product;
    }
}
