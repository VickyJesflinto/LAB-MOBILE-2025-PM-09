package com.example.instagramlikeapp;
import java.util.ArrayList;

public class User {
    private String username;
    private int profileImage;
    private ArrayList<Feed> posts;

    public User(String username, int profileImage, ArrayList<Feed> posts) {
        this.username = username;
        this.profileImage = profileImage;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<Feed> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Feed> posts) {
        this.posts = posts;
    }
}
