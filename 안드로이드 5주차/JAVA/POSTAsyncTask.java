package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class POSTAsyncTask extends AsyncTask<String, String, String> {
    private Context context;

    public POSTAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(4000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            OutputStream outputStream = connection.getOutputStream();
            DataOutputStream request = new DataOutputStream(outputStream);
            request.writeBytes(strings[1]);
            request.flush();
            request.close();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                while ((line = reader.readLine()) != null)
                    builder.append(line + '\n');
                reader.close();
                streamReader.close();
                inputStream.close();
            } else {
                publishProgress("통신 오류");
            }
            connection.disconnect();
        } catch (IOException e) {
            publishProgress(e.getMessage());
        }
        return builder.toString();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
    }
}
