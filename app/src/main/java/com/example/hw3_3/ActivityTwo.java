package com.example.hw3_3;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActivityTwo extends AppCompatActivity {

    private ImageView imgChoose;
    private EditText etTxt;
    private Button btnSend;
    private final int RESULT_LOAD_IMG = 10;
    public static final int REQUEST_CODE = 100;

    protected String textImage;
    protected Uri uri;
    protected int PICK_IMAGE;

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
                Intent intentData = new Intent();
                intentData.putExtra(MainActivity.TEXT_RESULT, etTxt.getText().toString().trim());
                intentData.putExtra("image", textImage);
                setResult(RESULT_OK, intentData);
                finish();
            }
        });
        imgChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null) {
            uri = data.getData();
            textImage = data.getDataString();
            imgChoose.setImageURI(uri);
        }
    }
}
