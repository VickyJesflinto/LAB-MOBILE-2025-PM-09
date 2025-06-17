package com.example.instagramlikeapp;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Feed> feeds;


    public FeedAdapter(Context context, ArrayList<Feed> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedview, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        Feed feed = feeds.get(position);
        holder.imgProfile.setImageResource(feed.getProfileImage());
        holder.tvUsername.setText(feed.getUsername());

//        cek apakah data gambar dari galery atau dari drawable
        if (feed.isFromGallery()) {
            holder.imgPost.setImageURI(Uri.parse(feed.getPostImageUri()));
        } else {
            holder.imgPost.setImageResource(feed.getPostImageRes());
        }

        String caption = feed.getUsername() +": "+ feed.getCaption();
        holder.tvCaption.setText(caption);


        View.OnClickListener profileClickListener = v -> {
            Intent intent = new Intent(context, profile.class);
            intent.putExtra("username", feed.getUsername());
            intent.putExtra("profileImage", feed.getProfileImage());
            context.startActivity(intent);
        };
        holder.tvUsername.setOnClickListener(profileClickListener);
        holder.imgProfile.setOnClickListener(profileClickListener);
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgProfile;
        TextView tvUsername;
        ImageView imgPost;
        TextView tvCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_profile);
            tvUsername = itemView.findViewById(R.id.tv_username);
            imgPost = itemView.findViewById(R.id.img_post);
            tvCaption = itemView.findViewById(R.id.tv_caption);
        }
    }
}

