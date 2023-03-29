package com.example.imagejson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.image);
        TextView model = findViewById(R.id.model);
        TextView brand = findViewById(R.id.brand);
        TextView price = findViewById(R.id.price);
        TextView desc = findViewById(R.id.desc);
        TextView detail = findViewById(R.id.detail);

        Intent intent = getIntent();
        model.setText(intent.getStringExtra("model"));
        brand.setText(intent.getStringExtra("brand"));
        price.setText(intent.getStringExtra("price"));
        desc.setText(intent.getStringExtra("desc"));
        detail.setText(intent.getStringExtra("detail"));
        ImageThread thread = new ImageThread(Detail.this, intent.getStringExtra("image"));
        thread.start();
        try {
            thread.join();
            imageView.setImageBitmap(thread.getBitmap());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}