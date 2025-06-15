package com.example.instagramlikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class highligh_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_highligh_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView imgHighlight = findViewById(R.id.img_main);
        TextView tvUsername = findViewById(R.id.tv_username);
        ImageView imgProfile = findViewById(R.id.img_profile);

        Intent intent = getIntent();
        int highlightImage = intent.getIntExtra("highlightImage", -1);
        String username = intent.getStringExtra("username");
        int profileImage = intent.getIntExtra("profileImage", -1);

        imgHighlight.setImageResource(highlightImage);
        tvUsername.setText(username);
        imgProfile.setImageResource(profileImage);

    }
}

