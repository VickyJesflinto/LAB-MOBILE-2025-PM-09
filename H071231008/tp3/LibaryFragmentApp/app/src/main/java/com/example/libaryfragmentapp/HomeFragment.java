package com.example.libaryfragmentapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private SearchView searchView;
    private ChipGroup genreChipGroup;
    private ArrayList<Book> allBooks;
    private ArrayList<Book> filteredBooks = new ArrayList<>();
    private String currentSearchQuery = "";
    private String selectedGenre = "";

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());
    private View progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.search_view);
        genreChipGroup = view.findViewById(R.id.genre_chip_group);
        recyclerView = view.findViewById(R.id.recycler_view_books);
        progressBar = view.findViewById(R.id.progressBar);

        allBooks = DataBook.bookList;
        filteredBooks.addAll(allBooks);

        setupGenreChips();
        setupSearchView();
        setupRecyclerView();


        return view;
    }
    private void setupGenreChips() {
        Chip allChip = new Chip(requireContext());
        allChip.setText("All");
        allChip.setCheckable(true);
        allChip.setChecked(true);
        allChip.setClickable(true);
        genreChipGroup.addView(allChip);

        Set<String> genres = new HashSet<>();
        for (Book book : allBooks) {
            genres.add(book.genre);
        }

        for (String genre : genres) {
            Chip chip = new Chip(requireContext());
            chip.setText(genre);
            chip.setCheckable(true);
            chip.setClickable(true);
            genreChipGroup.addView(chip);
        }

        genreChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            ArrayList<String> selectedGenres = new ArrayList<>();
            for (int id : checkedIds) {
                Chip selectedChip = group.findViewById(id);
                if (selectedChip != null) {
                    String genre = selectedChip.getText().toString();
                    if (!genre.equals("All")) {
                        selectedGenres.add(genre);
                    }
                }
            }

            if (selectedGenres.isEmpty()) {
                allChip.setChecked(true);
            } else {
                allChip.setChecked(false);
            }

            selectedGenre = String.join(",", selectedGenres);
            filterBooks();
        });
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentSearchQuery = newText;
                filterBooks();
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new BookAdapter(getContext(), filteredBooks, book -> {
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
    }

    private void filterBooks() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ArrayList<Book> tempFiltered = new ArrayList<>();

            for (Book book : allBooks) {
                boolean matchesSearch = currentSearchQuery.isEmpty() ||
                        book.title.toLowerCase().contains(currentSearchQuery.toLowerCase());

                boolean matchesGenre = selectedGenre.isEmpty() ||
                        selectedGenre.contains(book.genre);

                if (matchesSearch && matchesGenre) {
                    tempFiltered.add(book);
                }
            }
            if (getActivity() != null){
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                });

            }

            handler.post(() -> {
                filteredBooks.clear();
                filteredBooks.addAll(tempFiltered);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            });
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}