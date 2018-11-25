package com.dthang.myapp.handleservice;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadJSON extends AsyncTask<String,Void,String> {

    private static final String TAG = "DownloadJSON";
    
    private String strUrl;
    private List<HashMap<String,String>> attrs;
    private StringBuffer data;
    private boolean isPost = false;

    public DownloadJSON(String url) {
        this.strUrl = url;
        isPost = false;
    }

    public DownloadJSON(String url,List<HashMap<String,String>> attrs){
        isPost = true;
        this.strUrl = url;
        this.attrs = attrs;
    }

    @Override
    protected String doInBackground(String... strings) {
        String _data = "";
        try {
            URL url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (isPost){
                _data = methodPost(httpURLConnection);
                Log.d(TAG, "doInBackground: this is POST" );
            }else {
                _data = methodGet(httpURLConnection);
                Log.d(TAG, "doInBackground: this is GET" );
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return _data;
    }

    private String methodGet(HttpURLConnection httpURLConnection){
        String _data = "";
        try {
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            data = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine())!=null){
                data.append(line);
            }

            _data = data.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return _data;
    }

    private String methodPost(HttpURLConnection httpURLConnection){
        String _data = "";
        String key = "";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();

            int attrs_count = attrs.size();
            for (int i = 0; i < attrs_count; i++) {
                for(Map.Entry<String,String> values : attrs.get(i).entrySet()){
                    key = values.getKey();
                    value = values.getValue();
                }
                builder.appendQueryParameter(key,value);
            }

            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

            _data = methodGet(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "methodPost: "+_data);
        return _data;
    }
}
