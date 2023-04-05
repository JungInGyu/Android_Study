package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    private Context context;


    public JSONParser(Context context) {
        this.context = context;
    }

    public String postParsing(String json) {
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject object = root.getJSONObject((String) root.names().get(3));
            String address = object.getString((String) object.names().get(0));
            String age = object.getString((String) object.names().get(1));
            String name = object.getString((String) object.names().get(2));
            builder.append("이름 : " + name + "\n");
            builder.append("나이 : " + age + " 세 \n");
            builder.append("주소 : " + address + "\n");

        } catch (JSONException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT);
        }
        return builder.toString();
    }
        public String parsing (String json){
            StringBuilder builder = new StringBuilder();
            try {
                JSONObject root = new JSONObject(json);
                JSONObject object = root.getJSONObject((String) root.names().get(0));
                String address = object.getString((String) object.names().get(0));
                String age = object.getString((String) object.names().get(1));
                String name = object.getString((String) object.names().get(2));
                builder.append("이름 : " + name + "\n");
                builder.append("나이 : " + age + " 세 \n");
                builder.append("주소 : " + address + "\n");

            } catch (JSONException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT);
            }
            return builder.toString();
        }
    }
