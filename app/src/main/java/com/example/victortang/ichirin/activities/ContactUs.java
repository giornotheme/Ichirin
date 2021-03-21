package com.example.victortang.ichirin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.victortang.ichirin.R;

public class ContactUs extends AppCompatActivity {

    private Button mInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        mInformation = findViewById(R.id.information);

        mInformation.setOnClickListener(v -> {
            Intent informationActivity = new Intent(ContactUs.this, Information.class);
            startActivity(informationActivity);
        });
    }
}