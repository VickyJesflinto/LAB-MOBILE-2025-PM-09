package com.example.instagramlikeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class add extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private Uri selectedImageUri;
    private ImageView imgPostPreview;
    private EditText etCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgPostPreview = findViewById(R.id.img_post_preview);
        etCaption = findViewById(R.id.et_caption);

        Button btnSelectImage = findViewById(R.id.btn_select_image);
        btnSelectImage.setOnClickListener(v -> openGallery());

        Button btnPost = findViewById(R.id.btn_post);
        btnPost.setOnClickListener(v -> submitPost());



//        setup botnav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnItemSelectedListener(item -> {
            Intent intent = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                intent = new Intent(add.this, MainActivity.class);
            } else if (itemId == R.id.nav_add) {
                intent = new Intent(add.this, add.class);
            } else if (itemId == R.id.nav_profile) {
                intent = new Intent(add.this, profile.class);
            }

            if (intent != null) {
                startActivity(intent);
                return true;
            }

            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imgPostPreview.setImageURI(selectedImageUri);
        }
    }

    private void submitPost() {
        if (selectedImageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }
//        minta izin parmanen read gambar
        try {
            getContentResolver().takePersistableUriPermission(
                    selectedImageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            );
        } catch (SecurityException e) {
            // Log error
            e.printStackTrace();
        }

        String caption = etCaption.getText().toString();

        String imageUriString = selectedImageUri.toString();
        User currentUser = DataSource.users.get(0);
        currentUser.getPosts().add(0, new Feed(imageUriString, caption, currentUser.getUsername(), currentUser.getProfileImage()));

        Toast.makeText(this, "Post added!", Toast.LENGTH_SHORT).show();

        finish();
    }


    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

}