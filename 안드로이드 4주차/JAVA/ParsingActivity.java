package com.example.imagejson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ParsingActivity extends AppCompatActivity {
    int type = 1;
    ArrayList<NoteBook> noteBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);
        setTitle("Parsing 하기");
        Intent intent = getIntent();
        String page = intent.getStringExtra("page");
        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NoteBook noteBook = noteBooks.get(position);
                Intent intent1 = new Intent(ParsingActivity.this, Detail.class);
                intent1.putExtra("model", noteBook.getModel());
                intent1.putExtra("brand", noteBook.getBrand());
                intent1.putExtra("price", noteBook.getPrice());
                intent1.putExtra("desc", noteBook.getDesc());
                intent1.putExtra("detail", noteBook.getDetail());
                intent1.putExtra("image", noteBook.getImage());
                startActivity(intent1);
            }
        });
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1) {
                    JSONThread thread = new JSONThread(ParsingActivity.this, page);
                    thread.start();
                    try {
                        thread.join();
                        JSONParser parser = new JSONParser(ParsingActivity.this);
                        noteBooks = parser.Parsing(thread.getResuit());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    CustomAdapter adapter = new CustomAdapter(ParsingActivity.this, noteBooks);
                    listView.setAdapter(adapter);
                } else {

                }
            }
        });

        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                type = 1;
                break;
            case R.id.item2:
                type = 2;
                break;
        }
        item.setChecked(true);
        return true;
    }
}