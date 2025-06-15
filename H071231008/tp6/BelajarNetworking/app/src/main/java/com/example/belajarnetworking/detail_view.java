package com.example.belajarnetworking;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class detail_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        // Get data from Intent
        String name = getIntent().getStringExtra("name");
        String status = getIntent().getStringExtra("status");
        String species = getIntent().getStringExtra("species");
        String image = getIntent().getStringExtra("image");
        String gender = getIntent().getStringExtra("gender");

        // Bind views
        TextView nameTextView = findViewById(R.id.detail_name);
        TextView statusTextView = findViewById(R.id.detail_status);
        TextView speciesTextView = findViewById(R.id.detail_species);
        ImageView imageView = findViewById(R.id.detail_image);
        TextView genderText = findViewById(R.id.detail_gender);

        // Set data to views
        nameTextView.setText(name);
        statusTextView.setText(status);
        speciesTextView.setText(species);
        genderText.setText(gender);
        Glide.with(this).load(image).into(imageView);
    }
}