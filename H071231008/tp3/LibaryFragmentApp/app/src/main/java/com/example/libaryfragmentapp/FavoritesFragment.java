package com.example.libaryfragmentapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private ArrayList<Book> allBooks;
    private ProgressBar progressBar;
    private Handler handler = new Handler(Looper.getMainLooper());
    private ExecutorService executor = Executors.newSingleThreadExecutor();


    private TextView noFav;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_books);
        progressBar = view.findViewById(R.id.progress_bar);
        noFav = view.findViewById(R.id.empty_view);

        allBooks = new ArrayList<>();
        adapter = new BookAdapter(getContext(), allBooks, book -> {
            Intent intent = new Intent(getContext(), BookDetail.class);
            intent.putExtra("title", book.title);
            intent.putExtra("author", book.author);
            intent.putExtra("genre", book.genre);
            intent.putExtra("year", book.year);
            intent.putExtra("blurb", book.blurb);
            intent.putExtra("liked", book.isLiked);
            intent.putExtra("rating", book.rating);
            intent.putExtra("imageUri", book.imageUri);
            startActivity(intent);
        });

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        loadFavoriteBooks(); // 👈 Simulated loading here

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        allBooks.clear();
        for (Book book : DataBook.bookList) {
            if (book.isLiked) {
                allBooks.add(book);
            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void loadFavoriteBooks() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        noFav.setVisibility(View.GONE);

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<Book> likedBooks = new ArrayList<>();
            for (Book book : DataBook.bookList) {
                if (book.isLiked) {
                    likedBooks.add(book);
                }
            }

            handler.post(() -> {
                allBooks.clear();
                allBooks.addAll(likedBooks);
                adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);

                if (allBooks.isEmpty()) {
                    noFav.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    noFav.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        });
    }

}