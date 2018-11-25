package com.dthang.myapp.model.product;

import android.util.Log;

import com.dthang.myapp.handleservice.DownloadJSON;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.util.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MoldeProduct {

    private static final String TAG = "MoldeProduct";

    public List<Product> getListProduct(String function, int id,int limit) {
        List<Product> mListProduct = new ArrayList<>();

        String strURL = App._URL;

        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("handle", function);
        attrs.add(hashMap);

        if (function.equals("getProductFromTrademark")) {
            HashMap<String, String> hashMapID = new HashMap<>();
            hashMapID.put("trademark_id", String.valueOf(id));
            attrs.add(hashMapID);
        } else {
            HashMap<String, String> hashMapID = new HashMap<>();
            hashMapID.put("product_type_id", String.valueOf(id));
            attrs.add(hashMapID);
        }

        HashMap<String, String> hashMapLimit = new HashMap<>();
        hashMapLimit.put("limit", String.valueOf(limit));
        attrs.add(hashMapLimit);


        DownloadJSON downloadJSON = new DownloadJSON(strURL, attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();

            JSONArray jArrayProduct = new JSONArray(data);
            int length = jArrayProduct.length();
            for (int i = 0; i < length; i++) {
                JSONObject jProduct = jArrayProduct.getJSONObject(i);
                int id1 = Integer.valueOf(jProduct.getString("PRODUCT_ID"));
                String name = jProduct.getString("PRODUCT_NAME");
                int price = Integer.valueOf(jProduct.getString("PRODUCT_PRICE"));
                String image = jProduct.getString("PRODUCT_BIG_IMAGE");
                Product product = new Product(id1, price, name,  image);
                mListProduct.add(product);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, ":getListProduct " + mListProduct.size());
        return mListProduct;
    }

}
