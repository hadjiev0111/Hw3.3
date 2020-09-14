package com.example.hw3_3;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTwo extends AppCompatActivity {
    private ImageView imgChoose;
    private EditText etTxt;
    private Button btnSend;
    private Uri imageData;
    public static String KEY = "key";
    public static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        init();
    }

    private void init() {
        imgChoose = findViewById(R.id.imgChoose);
        etTxt = findViewById(R.id.etTxt);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
        imgChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImg(view);
            }
        });
    }

    private void sendData() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("info", etTxt.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void chooseImg(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser (intent, "choose img"), 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 200 && resultCode == RESULT_OK);
        imageData = data.getData();
        imgChoose.setImageURI(imageData);
    }
}
