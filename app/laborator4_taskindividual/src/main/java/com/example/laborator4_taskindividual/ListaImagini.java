package com.example.laborator4_taskindividual;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaImagini extends AppCompatActivity {

    private List<ImaginiMetrou> listaImagini = new ArrayList<>();
    private List<Bitmap> imaginiDescarcate=new ArrayList<android.graphics.Bitmap>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini= new ArrayList<>();
        linkuriImagini.add("https://www.antena3.ro/thumbs/big4/2024/01/30/imagini-metrou-nou-bucuresti-magistrala-circulatie-alstrom-metropolis-883821.jpg");
        linkuriImagini.add("https://mihaicraciun.eu/wp-content/uploads/2021/03/Undeground.jpeg");
        linkuriImagini.add("https://i0.wp.com/mobilitate.eu/wp-content/uploads/2024/05/DSC_7235-2.jpg");
        linkuriImagini.add("https://media.radiochisinau.md/image/201709/640x360/946274f585f3d6bede34038094807386.jpg");
        linkuriImagini.add("https://st5.depositphotos.com/35931800/64632/i/450/depositphotos_646326586-stock-photo-march-2023-madrid-spain-subway.jpg");


        ListView lv= findViewById(R.id.imagineSiText_LV);

        Executor executor= Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con = null;
                    try {
                        URL url=new URL(link);
                        con=(HttpURLConnection) url.openConnection();
                        InputStream is= con.getInputStream();
                        imaginiDescarcate.add(BitmapFactory.decodeStream(is));

                    }  catch (IOException e) {
                        imaginiDescarcate.add(null);
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listaImagini= new ArrayList<>();
                        listaImagini.add(new ImaginiMetrou("Metrou Bucuresti", imaginiDescarcate.get(0),"https://www.metrorex.ro/"));
                        listaImagini.add(new ImaginiMetrou("Metrou Londra", imaginiDescarcate.get(1),"https://tfl.gov.uk/maps/track/tube"));
                        listaImagini.add(new ImaginiMetrou("Metrou Viena", imaginiDescarcate.get(2),"https://www.wienerlinien.at/web/wl-en/network-maps"));
                        listaImagini.add(new ImaginiMetrou("Metrou Berlin", imaginiDescarcate.get(3),"https://www.berlin.de/en/public-transportation/1742343-2913840-underground-subway.en.html"));
                        listaImagini.add(new ImaginiMetrou("Metrou Madrid", imaginiDescarcate.get(4),"https://www.metromadrid.es/en/travel-in-the-metro/metro-de-madrid-maps"));

                        AdapterImagine adapter= new AdapterImagine(listaImagini, getApplicationContext(), R.layout.item_imagine);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });

        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(),WebViewActivity.class);
                it.putExtra("link",listaImagini.get(i).getLink());
                startActivity(it);
            }
        });
    }
}