package com.example.belajarnetworking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private TextView maaf;
    private int curpage;
    private Button gotopage2;
    private List<Character> allCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        maaf = findViewById(R.id.maaf);
        gotopage2 = findViewById(R.id.gotopage2);
        maaf.setVisibility(View.GONE);
        curpage = 1;
        allCharacters = new ArrayList<>();
        characterAdapter = new CharacterAdapter(allCharacters);
        recyclerView.setAdapter(characterAdapter);

        loadCharacters(curpage, curpage + 1);

        gotopage2.setOnClickListener(v -> {
            curpage += 2;
            loadCharacters(curpage, curpage + 1);
        });
    }

    private void loadCharacters(int startPage, int endPage) {
        for (int page = startPage; page <= endPage; page++) {
            Call<CharacterResponse> call = apiService.getCharacters(page);
            call.enqueue(new Callback<CharacterResponse>() {
                @Override
                public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Character> characters = response.body().getResults();
                        allCharacters.addAll(characters);
                        characterAdapter.notifyDataSetChanged();
                    } else {
                        maaf.setVisibility(View.VISIBLE);
                        gotopage2.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<CharacterResponse> call, Throwable t) {
                    maaf.setVisibility(View.VISIBLE);
                    gotopage2.setVisibility(View.GONE);
                }
            });
        }
    }
}