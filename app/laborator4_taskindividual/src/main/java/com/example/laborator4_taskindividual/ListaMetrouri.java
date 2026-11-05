package com.example.laborator4_taskindividual;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import java.util.List;

public class ListaMetrouri extends AppCompatActivity {
    private ListView listView;
    private AdapterMetrou adapter = null;
    private List<Metrou> lista = new ArrayList<>();
    private int idModificat =0;

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
            ArrayList<Metrou> primita = bundle.getParcelableArrayList("lista");
            if (primita != null) {
                lista = primita;
            }
        }


        adapter = new AdapterMetrou(lista, getApplicationContext(), R.layout.item_metrou);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentModifica = new Intent(getApplicationContext(),AddMetrouActivity.class);
                intentModifica.putExtra("metrou", lista.get(position));
                idModificat=position;
                startActivityForResult(intentModifica,209);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==209 && resultCode==RESULT_OK && data!=null){
            Metrou modificat = data.getParcelableExtra("metrou");
            if(modificat != null && idModificat>=0 && idModificat<lista.size()){
                lista.set(idModificat,modificat);
                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
                Toast.makeText(this, "Metrou modificat!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void trimiteListaInapoi() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("lista", (ArrayList<? extends Parcelable>) lista);

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }
}