package com.example.belajarthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.belajarthread.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
//    private Button btn_start;
//    private TextView tv_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        btn_start = findViewById(R.id.btn_start);
//        tv_status = findViewById(R.id.tv_status);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        binding.btnStart.setOnClickListener(v -> {
            executor.execute(() -> {
                try {
                    for (int i = 0; i <= 10; i++) {
                        Thread.sleep(500);
                        int percentage = i * 10;
                        handler.post(() -> {
                            if (percentage == 100) {
                                binding.tvStatus.setText(R.string.task_completed);
                            } else {
                                binding.tvStatus.setText(String.format(getString(R.string.compressing), percentage));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}