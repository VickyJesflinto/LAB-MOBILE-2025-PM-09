package com.example.belajarnetworking;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Character> characterList;

    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.nameTextView.setText(character.getName());
        holder.statusTextView.setText(character.getStatus());
        holder.speciesTextView.setText(character.getSpecies());

        Glide.with(holder.itemView.getContext())
                .load(character.getImage())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), detail_view.class);
            intent.putExtra("name", character.getName());
            intent.putExtra("status", character.getStatus());
            intent.putExtra("species", character.getSpecies());
            intent.putExtra("image", character.getImage());
            intent.putExtra("gender", character.getGender());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, statusTextView, speciesTextView;
        ImageView imageView;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.character_name);
            statusTextView = itemView.findViewById(R.id.character_status);
            speciesTextView = itemView.findViewById(R.id.character_species);
            imageView = itemView.findViewById(R.id.character_image);
        }
    }
}