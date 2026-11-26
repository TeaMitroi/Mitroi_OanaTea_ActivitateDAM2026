package com.example.laborator9;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lista_vreme extends AppCompatActivity {

    private static final String apiKey= "";
    private static final String tag="Lista_vreme";

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());


    private EditText oras_ET;
    private TextView orasCautat_TV;
    private Spinner zile_SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_vreme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        oras_ET= findViewById(R.id.oras_ET);
        orasCautat_TV= findViewById(R.id.rezultat_TV);
        zile_SP= findViewById(R.id.zile_SP);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"1 zi", "5 zile"});
        zile_SP.setAdapter(adapter);

        Button btnCautare=findViewById(R.id.butonAPI_BTN);

        btnCautare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oras=oras_ET.getText().toString().trim();
                if(oras.isEmpty()){
                    Toast.makeText(Lista_vreme.this, "Introdu un oraș", Toast.LENGTH_SHORT).show();
                    return;
                }
                String metoda = (zile_SP.getSelectedItemPosition() == 0) ? "1day" : "5day";
                CautareVreme(oras, metoda);
            }

            private void CautareVreme(String oras, String metoda) {
                orasCautat_TV.setText("Se încarcă...");

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String linkOras = "https://dataservice.accuweather.com/locations/v1/cities/search"
                                    + "?apikey=" + apiKey
                                    + "&q=" + URLEncoder.encode(oras, "UTF-8");

                            JSONArray vector = new JSONArray(apelHttp(linkOras));
                            if (vector.length() == 0) {
                                handler.post(() -> orasCautat_TV.setText("Orașul \"" + oras + "\" nu a fost găsit."));
                                return;
                            }
                            JSONObject obiect = vector.getJSONObject(0);
                            String cheieOras = obiect.getString("Key");

                            Log.d(tag, "Cheia orasului: " + cheieOras);

                            handler.post(() -> {
                                orasCautat_TV.setText("Cod oraș: " + cheieOras + "\nSe încarcă prognoza...");
                                Toast.makeText(Lista_vreme.this,
                                        "Key: " + cheieOras, Toast.LENGTH_SHORT).show();
                            });
                            String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/"
                                    + metoda + "/" + cheieOras
                                    + "?apikey=" + apiKey
                                    + "&metric=true";   // Celsius

                            JSONObject radacina = new JSONObject(apelHttp(linkVreme));
                            JSONArray prognoze = radacina.getJSONArray("DailyForecasts");

                            StringBuilder rezultat = new StringBuilder();
                            rezultat.append("Cod oraș: ").append(cheieOras).append("\n\n");

                            // la 1 zi avem 1 element, la 5 zile avem 5 -> le afisam pe toate
                            for (int i = 0; i < prognoze.length(); i++) {
                                JSONObject zi = prognoze.getJSONObject(i);
                                String data = zi.getString("Date").substring(0, 10);

                                JSONObject temperatura = zi.getJSONObject("Temperature");
                                double min = temperatura.getJSONObject("Minimum").getDouble("Value");
                                double max = temperatura.getJSONObject("Maximum").getDouble("Value");

                                rezultat.append(data).append("\n")
                                        .append("Min: ").append(min).append(" °C\n")
                                        .append("Max: ").append(max).append(" °C\n\n");
                            }

                            final String text = rezultat.toString();
                            handler.post(() -> orasCautat_TV.setText(text));

                        } catch (Exception e) {
                            Log.e(tag, "Eroare", e);
                            handler.post(() -> orasCautat_TV.setText("Eroare: " + e.getMessage()));
                        }
                    }
                });
            }

            private String apelHttp(String linkVreme) throws Exception{
                URL url = new URL(linkVreme);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                int cod = conn.getResponseCode();
                if (cod != HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                    throw new Exception("HTTP " + cod);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder builder = new StringBuilder("");
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
                conn.disconnect();
                return builder.toString();
            }

//            private void CautareOras(String oras) {
//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        try{
//                            String link="https://dataservice.accuweather.com/locations/v1/cities/search"
//                                    + "?apikey=" + apiKey
//                                    + "&q=" + URLEncoder.encode(oras, "UTF-8");
//                            URL url = new URL(link);
//                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                            BufferedReader reader = new BufferedReader(
//                                    new InputStreamReader(conn.getInputStream()));
//                            StringBuilder builder = new StringBuilder("");
//                            String line;
//                            while ((line = reader.readLine()) != null) {
//                                builder.append(line);
//                            }
//                            reader.close();
//                            conn.disconnect();
//
//                            JSONArray vector = new JSONArray(builder.toString());
//                            JSONObject obiect = vector.getJSONObject(0);
//                            String cheieOras = obiect.getString("Key");
//
//                            Log.d("WeatherActivity", "Cheia orasului: " + cheieOras);
//
//                            handler.post(() -> {
//                                orasCautat_TV.setText("Cod oraș: " + cheieOras);
//                                Toast.makeText(Lista_vreme.this,
//                                        "Key: " + cheieOras, Toast.LENGTH_SHORT).show();
//                            });
//                        } catch (UnsupportedEncodingException e) {
//                            throw new RuntimeException(e);
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        } catch (MalformedURLException e) {
//                            throw new RuntimeException(e);
//                        } catch (IOException e) {
//                            Log.e("WeatherActivity", "Eroare", e);
//                            handler.post(() -> orasCautat_TV.setText("Eroare: " + e.getMessage()));
//                        }
//                    }
//                });
//            }
       });

    }
}