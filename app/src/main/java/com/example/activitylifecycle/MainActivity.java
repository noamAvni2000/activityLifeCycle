package com.example.activitylifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activitylifecycle.R;

public class MainActivity extends AppCompatActivity {
    

    private long totalTime = 0;
    private long startTime = 0;

    private TextView totalScreenTimeTextView;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate");

        totalScreenTimeTextView = findViewById(R.id.totalScreenTimeTextView);
        restartButton = findViewById(R.id.restartButton);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");

        startTime = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");

        if (startTime != 0) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            totalTime += elapsedTime / 1000;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");

        if (startTime != 0) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            totalTime += elapsedTime / 1000;
        }

        updateTotalScreenTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }

    private void updateTotalScreenTime() {
        totalScreenTimeTextView.setText("Total Screen Time: " + totalTime + " seconds");
    }

    private void resetTimer() {
        totalTime = 0;
        startTime = 0;
        updateTotalScreenTime();
    }
}
