package com.example.victortang.ichirin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.victortang.ichirin.R;

public class Information extends AppCompatActivity {

    private Button mContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        mContactUs = findViewById(R.id.contactus);

        mContactUs.setOnClickListener(v -> {
            Intent contactActivity = new Intent(Information.this, ContactUs.class);
            startActivity(contactActivity);
        });
    }
}