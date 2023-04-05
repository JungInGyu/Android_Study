package com.example.myapplication;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class GetThread extends Thread{
    private Context context;
    private  String site;

    private Handler handler =new Handler();
    private StringBuilder builder =new StringBuilder();
    public GetThread(Context context, String site) {
        this.context = context;
        this.site = site;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(site);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(4000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                while ((line = reader.readLine()) != null)
                    builder.append((line + '\n'));
                    reader.close();
                    inputStream.close();
                    connection.disconnect();

            }
            }
             catch(IOException e){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "통신 오류", Toast.LENGTH_SHORT);
                    }
                });
            }
        }

    public  String getResult(){
        return builder.toString();
    }
}
