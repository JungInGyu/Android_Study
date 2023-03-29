package com.example.imagejson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SourceActivity extends AppCompatActivity {
    String json = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        setTitle("Source 보기");

        Intent intent = getIntent();
        String page = intent.getStringExtra("page");
        TextView textView = findViewById(R.id.textview);


            JSONThread thread = new JSONThread(SourceActivity.this, page);
            thread.start();
            try {
                thread.join();
                json = thread.getResuit();
                textView.setText(json);
            } catch (InterruptedException e) {
                Toast.makeText(SourceActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}