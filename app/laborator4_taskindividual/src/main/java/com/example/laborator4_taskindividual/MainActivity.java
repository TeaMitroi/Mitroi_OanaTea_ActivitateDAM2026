package com.example.laborator4_taskindividual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView rezultat_TV;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Metrou metrou = (Metrou) bundle.getSerializable("metrou");
                        if (metrou != null) {
                            rezultat_TV.setText(metrou.toString());
                            Toast.makeText(this, metrou.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rezultat_TV = findViewById(R.id.rezultat_TV);
        Button btnAdauga = findViewById(R.id.adauga_BTN);
        Button btnAdaugaAi = findViewById(R.id.adaugaAi_BTN);

        btnAdauga.setOnClickListener(v ->
                launcher.launch(new Intent(this, AddMetrouActivity.class)));

        btnAdaugaAi.setOnClickListener(v ->
                launcher.launch(new Intent(this, AddMetrouActivityAI.class)));
    }
}