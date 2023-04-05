package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    String page = "http://httpbin.org/";

    String json = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText1 = findViewById(R.id.edittext1);
        EditText editText2 = findViewById(R.id.edittext2);
        EditText editText3 = findViewById(R.id.edittext3);
        TextView textView = findViewById(R.id.textview1);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                String age = editText2.getText().toString();
                String address = editText3.getText().toString();
                if(name.equals("") || age.equals("") || address.equals("")){
                    Toast.makeText(MainActivity.this,"입력하세요", Toast.LENGTH_SHORT);
                } else {
                    String site = page + "get?name=" + name + "&age=" +age +
                            "&address=" + address;
                    GetThread thread = new GetThread(MainActivity.this, site);
                    thread.start();
                    try {
                        thread.join();
                        json = thread.getResult();
                        textView.setText(json);
                    } catch (InterruptedException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT);
                    }

                }
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (json.equals("")) {
                    Toast.makeText(MainActivity.this, "먼저 읽어 주세요", Toast.LENGTH_SHORT);

                }else {
                    JSONParser parser = new JSONParser(MainActivity.this);
                    textView.setText(parser.parsing(json));
                }
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText().toString();
                String age = editText2.getText().toString();
                String address = editText3.getText().toString();
                if (name.isEmpty() || age.isEmpty() || address.isEmpty()){
                    Toast.makeText(MainActivity.this, "입력 하세요", Toast.LENGTH_SHORT).show();
                } else {
                    String site = page + "post";
                    String message ="name=" + name + "&age=" + age +
                            "&address=" + address;
                    POSTAsyncTask task = new POSTAsyncTask(MainActivity.this);
                    try {
                        json = task.execute(site,message).get();
                        textView.setText(json);
                    } catch (ExecutionException | InterruptedException e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONParser parser = new JSONParser(MainActivity.this);
                textView.setText(parser.postParsing(json));
            }
        });
    }
}