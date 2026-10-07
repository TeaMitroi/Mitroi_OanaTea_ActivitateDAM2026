package com.example.laborator2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Seminar","OnStart");
        Log.v("Seminar","OnStart");
        Log.d("Seminar","OnStart");
        Log.i("Seminar","OnStart");
        Log.w("Seminar","OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Seminar","OnResume");
        Log.v("Seminar","OnResume");
        Log.d("Seminar","OnResume");
        Log.i("Seminar","OnResume");
        Log.w("Seminar","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Seminar","OnPause");
        Log.v("Seminar","OnPause");
        Log.d("Seminar","OnPause");
        Log.i("Seminar","OnPause");
        Log.w("Seminar","OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Seminar","OnStop");
        Log.v("Seminar","OnStop");
        Log.d("Seminar","OnStop");
        Log.i("Seminar","OnStop");
        Log.w("Seminar","OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Seminar","OnDestroy");
        Log.v("Seminar","OnDestroy");
        Log.d("Seminar","OnDestroy");
        Log.i("Seminar","OnDestroy");
        Log.w("Seminar","OnDestroy");
    }
}