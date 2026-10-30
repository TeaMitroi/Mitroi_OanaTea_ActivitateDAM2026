package com.example.laborator4_taskindividual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Metrou> listaMetrouri = new ArrayList<>();
    private TextView rezultat_TV;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Metrou metrou = (Metrou) bundle.getSerializable("metrou");
                if (metrou != null) {
                    listaMetrouri.add(metrou);
                    Toast.makeText(this, "Metroul a fost adaugat in lista!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                ArrayList<Metrou> listaActualizata =
                        (ArrayList<Metrou>) bundle.getSerializable("lista");
                if (listaActualizata != null) {
                    listaMetrouri = listaActualizata;
                }
            }
        }
        rezultat_TV.setText("Metrouri in lista: " + listaMetrouri.size());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rezultat_TV = findViewById(R.id.rezultat_TV);

        Button btnAdauga = findViewById(R.id.adauga_BTN);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(), AddMetrouActivity.class);
                startActivityForResult(it,100);
            }
        });

        Button btnAdaugaAi = findViewById(R.id.adaugaAi_BTN);

        btnAdaugaAi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(), AddMetrouActivityAI.class);
                startActivityForResult(it,100);
            }
        });

        Button btnLista=findViewById(R.id.lista_BTN);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(), ListaMetrouri.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lista", listaMetrouri);
                it.putExtras(bundle);
                startActivityForResult(it,200);
            }
        });
    }
}