package com.example.hw3_3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.hw3_3.ActivityTwo.REQUEST_CODE;

public class MainActivity extends AppCompatActivity {
    protected ImageView imageView;
    private TextView textResult;
    private Button btnOpen, btnEmail;
    public static final String TEXT_RESULT = "textResult";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        textResult = findViewById(R.id.getTxt);
        btnOpen = findViewById(R.id.btnOpen);
        btnEmail = findViewById(R.id.btnEmail);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,ActivityTwo.class),REQUEST_CODE);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT,textResult.getText().toString().trim());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data!=null) {
            String text = data.getStringExtra(TEXT_RESULT);
            Uri uri = Uri.parse(data.getStringExtra("image"));
            textResult.setText(text);
            imageView.setImageURI(uri);
        }



    }
}