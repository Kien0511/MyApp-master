package com.dthang.myapp.model.home;

import com.dthang.myapp.handleservice.DownloadJSON;
import com.dthang.myapp.model.objectclass.ProductType;
import com.dthang.myapp.util.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DataHome {

    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String PRODUCT_TYPE_ID = "PRODUCT_TYPE_ID";
    public static final String PRODUCT_TYPE_NAME = "PRODUCT_TYPE_NAME";
    public static final String PRODUCT_TYPE_PARENTID = "PRODUCT_TYPE_PARENTID";

    public List<ProductType> parserJSONProductType(String data) {
        List<ProductType> mListProductType = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jProductTypes = jsonObject.getJSONArray(PRODUCT_TYPE);
            int length = jProductTypes.length();
            for (int i = 0; i < length; i++) {
                JSONObject value = jProductTypes.getJSONObject(i);

                int producttypeid = value.getInt(PRODUCT_TYPE_ID);
                String producttypename = value.getString(PRODUCT_TYPE_NAME);
                int producttypeparentid = value.getInt(PRODUCT_TYPE_PARENTID);

                ProductType productType = new ProductType(producttypeid, producttypeparentid, producttypename);
                mListProductType.add(productType);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mListProductType;
    }

    public List<ProductType> getListProductTypeChildren(int product_type_id) {

        List<HashMap<String, String>> arrts = new ArrayList<>();
        List<ProductType> mListProductTypes = new ArrayList<>();
        String data;

        HashMap<String, String> hashMapMenu = new HashMap<>();
        hashMapMenu.put("handle", "getMenus");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("product_type_parentid", String.valueOf(product_type_id));

        arrts.add(hashMapMenu);
        arrts.add(hashMap);

        DownloadJSON downloadJSON = new DownloadJSON(App._URL, arrts);

        downloadJSON.execute();

        try {
            data = downloadJSON.get();
            DataHome dataHome = new DataHome();
            mListProductTypes = dataHome.parserJSONProductType(data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return mListProductTypes;
    }
}
