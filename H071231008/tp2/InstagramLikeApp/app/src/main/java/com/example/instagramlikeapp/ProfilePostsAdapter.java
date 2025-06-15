package com.example.instagramlikeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfilePostsAdapter extends RecyclerView.Adapter<ProfilePostsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Feed> posts;

    public ProfilePostsAdapter(Context context, ArrayList<Feed> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed post = posts.get(position);

        if (post.isFromGallery()) {
            holder.imgPost.setImageURI(Uri.parse(post.getPostImageUri()));
        } else {
            holder.imgPost.setImageResource(post.getPostImageRes());
        }

        holder.imgPost.setOnClickListener(v -> {
            Intent intent = new Intent(context, detail_post.class);

            intent.putExtra("username", post.getUsername());
            intent.putExtra("caption", post.getCaption());
            intent.putExtra("profileImage", post.getProfileImage());
            intent.putExtra("isFromGallery", post.isFromGallery());

            if (post.isFromGallery()) {
                intent.putExtra("postImageUri", post.getPostImageUri());
            } else {
                intent.putExtra("postImageRes", post.getPostImageRes());
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.img_grid_post);
        }
    }
}