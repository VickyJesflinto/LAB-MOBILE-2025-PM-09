package com.example.instagramlikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    private CircleImageView imgProfile;
    private TextView tvUsername, tvUsernameToolbar, tvBio;
    private RecyclerView rvProfilePosts;
    User currentUser = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView highlight1 = findViewById(R.id.hl1);
        ImageView highlight2 = findViewById(R.id.hl2);
        ImageView highlight3 = findViewById(R.id.hl3);
        ImageView highlight4 = findViewById(R.id.hl4);
        ImageView highlight5 = findViewById(R.id.hl5);
        ImageView highlight6 = findViewById(R.id.hl6);
        ImageView highlight7 = findViewById(R.id.hl7);

        imgProfile = findViewById(R.id.img_profile);
        tvUsername = findViewById(R.id.tv_username);
        tvUsernameToolbar = findViewById(R.id.tv_username_toolbar);
        tvBio = findViewById(R.id.tv_bio);
        rvProfilePosts = findViewById(R.id.rv_profile_posts);

//        ambil nama dan cari used di db
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            for (User user : DataSource.users) {
                if (user.getUsername().equals(username)) {
                    currentUser = user;
                    break;
                }
            }
        }

        if (currentUser == null) {
            currentUser = DataSource.users.get(0);
        }

        setupProfileData(currentUser);

        setupRecyclerView(currentUser.getPosts());

        String usernameHl = currentUser.getUsername();
        int profileImage = currentUser.getProfileImage();

        // Handle klik tiap highlight
        highlight1.setOnClickListener(v -> openHighlightDetail(R.drawable.post1, usernameHl, profileImage));
        highlight2.setOnClickListener(v -> openHighlightDetail(R.drawable.post2, usernameHl, profileImage));
        highlight3.setOnClickListener(v -> openHighlightDetail(R.drawable.post3, usernameHl, profileImage));
        highlight4.setOnClickListener(v -> openHighlightDetail(R.drawable.post4, usernameHl, profileImage));
        highlight5.setOnClickListener(v -> openHighlightDetail(R.drawable.post5, usernameHl, profileImage));
        highlight6.setOnClickListener(v -> openHighlightDetail(R.drawable.post6, usernameHl, profileImage));
        highlight7.setOnClickListener(v -> openHighlightDetail(R.drawable.post7, usernameHl, profileImage));


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnItemSelectedListener(item -> {
            Intent intent = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                intent = new Intent(profile.this, MainActivity.class);
            } else if (itemId == R.id.nav_add) {
                intent = new Intent(profile.this, add.class);
            } else if (itemId == R.id.nav_profile) {
                intent = new Intent(profile.this, profile.class);
                // Pass the current user's username to maintain state when returning to profile
                intent.putExtra("username", currentUser.getUsername());
            }

            if (intent != null) {
                startActivity(intent);
                return true;
            }

            return false;

        });
    }

    private void setupProfileData(User user) {
        imgProfile.setImageResource(user.getProfileImage());

        tvUsername.setText(user.getUsername());
        tvUsernameToolbar.setText(user.getUsername());

        tvBio.setText("This is " + user.getUsername() + "'s profile. Check out my posts!");

        TextView tvPostsCount = findViewById(R.id.tv_posts_count);
        tvPostsCount.setText(String.valueOf(user.getPosts().size()));
    }

    private void setupRecyclerView(ArrayList<Feed> posts) {
        // bikin grid dengan span 3 (3 by something)
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvProfilePosts.setLayoutManager(layoutManager);

        ProfilePostsAdapter adapter = new ProfilePostsAdapter(this, posts);
        rvProfilePosts.setAdapter(adapter);
    }
    private void openHighlightDetail(int highlightImageRes, String username, int profileImageRes) {
        Intent intent = new Intent(this, highligh_detail.class);
        intent.putExtra("highlightImage", highlightImageRes);
        intent.putExtra("username", username);
        intent.putExtra("profileImage", profileImageRes);
        startActivity(intent);
    }

}