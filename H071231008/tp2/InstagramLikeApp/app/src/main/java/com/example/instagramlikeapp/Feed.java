package com.example.instagramlikeapp;

public class Feed {
    private String postImageUri;
    private int postImageRes = -1;
    private String caption;
    private String username;
    private int profileImage;

    public Feed(String postImageUri, String caption, String username, int profileImage) {
        this.postImageUri = postImageUri;
        this.caption = caption;
        this.username = username;
        this.profileImage = profileImage;
    }

    public Feed(int postImageRes, String caption, String username, int profileImage) {
        this.postImageRes = postImageRes;
        this.caption = caption;
        this.username = username;
        this.profileImage = profileImage;
    }

    public String getPostImageUri() {
        return postImageUri;
    }

    public int getPostImageRes() {
        return postImageRes;
    }

    public String getCaption() {
        return caption;
    }

    public String getUsername() {
        return username;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public boolean isFromGallery() {
        return postImageUri != null;
    }
}
