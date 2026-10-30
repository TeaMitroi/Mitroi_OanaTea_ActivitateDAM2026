package com.example.laborator4_taskindividual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaMetrouri extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Metrou> adapter;
    private ArrayList<Metrou> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_metrouri);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listaMetrouri_LV);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ArrayList<Metrou> primita = (ArrayList<Metrou>) bundle.getSerializable("lista");
            if (primita != null) {
                lista = primita;
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Metrou metrou = lista.get(position);
                Toast.makeText(ListaMetrouri.this, metrou.toString(), Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lista.remove(position);
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
                Toast.makeText(ListaMetrouri.this, R.string.metrou_sters, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    private void trimiteListaInapoi() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("lista", lista);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }
}