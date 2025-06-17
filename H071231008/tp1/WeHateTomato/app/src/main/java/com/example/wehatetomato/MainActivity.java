package com.example.wehatetomato;

import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView nameDisplay = findViewById(R.id.editName);
        TextView usernameDisplay = findViewById(R.id.editUsername);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");

        nameDisplay.setText(name != null ? name : "Andi Riswanda");
        usernameDisplay.setText(username != null ? username : "ciwanla");


        ImageView settingsIcon = findViewById(R.id.settingsIcon);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(profile);
            }
        });
        
        String profilePicUriString = getIntent().getStringExtra("profilePicUri");

        if (profilePicUriString != null) {
            Uri profilePicUri = Uri.parse(profilePicUriString);
            ImageView profilePicture = findViewById(R.id.lilypic);
            profilePicture.setImageURI(profilePicUri);
        }
    }

}

