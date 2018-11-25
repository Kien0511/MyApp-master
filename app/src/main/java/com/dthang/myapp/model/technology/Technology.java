package com.dthang.myapp.model.technology;

import android.util.Log;

import com.dthang.myapp.handleservice.DownloadJSON;
import com.dthang.myapp.model.objectclass.Product;
import com.dthang.myapp.model.objectclass.TradeMark;
import com.dthang.myapp.util.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Technology {

    private static final String TAG = "Technology";

    public List<TradeMark> getListTrademark(String function){
        List<TradeMark> markListTrademark = new ArrayList<>();

        String strURL = App._URL;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("handle",function);
        attrs.add(hashMap);

        DownloadJSON downloadJSON = new DownloadJSON(strURL,attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();

            JSONArray jArrayTrademark = new JSONArray(data);
            int length = jArrayTrademark.length();
            for (int i = 0; i < length; i++) {
                JSONObject jTrademark = jArrayTrademark.getJSONObject(i);
                int id = Integer.valueOf(jTrademark.getString("TRADEMARK_ID"));
                String name = jTrademark.getString("TRADEMARK_NAME");
                String image = jTrademark.getString("TRADEMARK_IMAGE");
                TradeMark tradeMark = new TradeMark(id,name,image);
                markListTrademark.add(tradeMark);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e(TAG, "handle: "+markListTrademark.size() );
        return markListTrademark;
    }

    public List<Product> getListTop10Product(String function){
        List<Product> mListProduct = new ArrayList<>();

        String strURL = App._URL ;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("handle",function);
        attrs.add(hashMap);


        DownloadJSON downloadJSON = new DownloadJSON(strURL,attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();

            JSONArray jArrayProduct = new JSONArray(data);
            int length = jArrayProduct.length();
            for (int i = 0; i < length; i++) {
                JSONObject jProduct = jArrayProduct.getJSONObject(i);
                int id = Integer.valueOf(jProduct.getString("PRODUCT_ID"));
                String name = jProduct.getString("PRODUCT_NAME");
                int price = Integer.valueOf(jProduct.getString("PRODUCT_PRICE"));
                String image = jProduct.getString("PRODUCT_BIG_IMAGE");
                Product product = new Product(id,price,name,image);
                mListProduct.add(product);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, ":getListTop10Product "+mListProduct.size() );
        return mListProduct;
    }

    public List<TradeMark> getListLogoTrademark(){
        List<TradeMark> markListTrademark = new ArrayList<>();

        String strURL = App._URL;

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("handle","getLogoTrademark");
        attrs.add(hashMap);

        DownloadJSON downloadJSON = new DownloadJSON(strURL,attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();

            JSONArray jArrayTrademark = new JSONArray(data);
            int length = jArrayTrademark.length();
            for (int i = 0; i < length; i++) {
                JSONObject jTrademark = jArrayTrademark.getJSONObject(i);
                int id = Integer.valueOf(jTrademark.getString("TRADEMARK_ID"));
                String name = jTrademark.getString("TRADEMARK_NAME");
                String image = jTrademark.getString("TRADEMARK_IMAGE");
                TradeMark tradeMark = new TradeMark(id,name,image);
                markListTrademark.add(tradeMark);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "handle: "+markListTrademark.size() );
        return markListTrademark;
    }


}
