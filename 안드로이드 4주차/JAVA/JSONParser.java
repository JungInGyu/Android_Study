package com.example.imagejson;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private Context context;

    public JSONParser(Context context) {
        this.context = context;
    }

    public ArrayList<NoteBook> Parsing(String json) {
        ArrayList<NoteBook> noteBooks = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject temp = root.getJSONObject((String) root.names().get(0));
            JSONArray array = temp.getJSONArray((String) temp.names().get(0));
            for (int i = 0; i < array.length(); i++) {
                NoteBook noteBook = new NoteBook();
                JSONObject object = array.getJSONObject(i);
                String model = object.getString((String) object.names().get(0));
                String brand = object.getString((String) object.names().get(1));
                String price = object.getString((String) object.names().get(2));
                String desc = object.getString((String) object.names().get(3));
                String detail = object.getString((String) object.names().get(4));
                String image = object.getString((String) object.names().get(5));
                noteBook.setModel(model);
                noteBook.setBrand(brand);
                noteBook.setPrice(price);
                noteBook.setDesc(desc);
                noteBook.setDetail(detail);
                noteBook.setImage(image);
                noteBooks.add(noteBook);
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return noteBooks;
    }
}
