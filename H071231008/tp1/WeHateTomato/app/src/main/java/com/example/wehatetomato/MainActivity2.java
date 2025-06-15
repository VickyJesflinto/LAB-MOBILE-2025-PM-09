package com.example.wehatetomato;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView profilePicture;
    private Uri profilePicUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        profilePicture = findViewById(R.id.profile_picture);
        TextView changeProfilePicBtn = findViewById(R.id.btnChanceProfile);

        changeProfilePicBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(v -> {

            EditText nameEdit = findViewById(R.id.inputName);
            EditText usernameEdit = findViewById(R.id.inputUsername);

            String name = nameEdit.getText().toString();
            String username = usernameEdit.getText().toString();

            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("username", username);
            if (profilePicUri != null) {
                intent.putExtra("profilePicUri", profilePicUri.toString());
            }
            startActivity(intent);
        });




    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            profilePicUri = selectedImageUri;
            profilePicture.setImageURI(selectedImageUri);
            profilePicture.setBackgroundResource(R.drawable.rounded_border);
        }
    }
}