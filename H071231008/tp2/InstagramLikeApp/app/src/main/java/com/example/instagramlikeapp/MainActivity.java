package com.example.instagramlikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFeed = findViewById(R.id.rv_feed);

        ArrayList<Feed> allFeeds = new ArrayList<>();

//        Untuk Post yang fotonya dari galery
        for (User user : DataSource.users) {
            for (Feed post : user.getPosts()) {

                if (post.isFromGallery()) {

                    Feed feedWithUserInfo = new Feed(
                            post.getPostImageUri(),
                            post.getCaption(),
                            user.getUsername(),
                            user.getProfileImage()
                    );
                    allFeeds.add(feedWithUserInfo);
                } else {
//                    untuk yang dari drawable
                    Feed feedWithUserInfo = new Feed(
                            post.getPostImageRes(),
                            post.getCaption(),
                            user.getUsername(),
                            user.getProfileImage()
                    );
                    allFeeds.add(feedWithUserInfo);
                }
            }
        }

//        pasang di rv pake adapter
        FeedAdapter adapter = new FeedAdapter(this, allFeeds);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        rvFeed.setAdapter(adapter);


//        handle botomnav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnItemSelectedListener(item -> {
            Intent intent = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                intent = new Intent(MainActivity.this, MainActivity.class);
            } else if (itemId == R.id.nav_add) {
                intent = new Intent(MainActivity.this, add.class);
            } else if (itemId == R.id.nav_profile) {
                intent = new Intent(MainActivity.this, profile.class);
            }

            if (intent != null) {
                startActivity(intent);
                return true;
            }

            return false;
        });

    }

}
