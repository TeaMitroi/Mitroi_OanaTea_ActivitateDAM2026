package com.example.laborator4_taskindividual;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMetrouActivity extends AppCompatActivity {
    protected int getLayoutId() {
        return R.layout.activity_activitate_adaugare;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        EditText id_ET = findViewById(R.id.id_ET);
        EditText model_ET = findViewById(R.id.model_ET);
        EditText vagoane = findViewById(R.id.vagoane_ET);
        EditText statii = findViewById(R.id.statii_ET);
        Spinner magistrala_SP = findViewById(R.id.magistrala_SP);
        RadioGroup traseu_RG = findViewById(R.id.traseu_RG);
        RatingBar uzura = findViewById(R.id.uzura_RB);
        CheckBox aer = findViewById(R.id.aer_CB);
        CompoundButton accesibil = findViewById(R.id.accesibil_SW);
        Button btnSalveaza = findViewById(R.id.salveaza_BTN);

        btnSalveaza.setOnClickListener(v -> {
            String idText = id_ET.getText().toString().trim();
            String model = model_ET.getText().toString().trim();
            String vagoaneText = vagoane.getText().toString().trim();
            String statiiText = statii.getText().toString().trim();

            if (idText.isEmpty() || model.isEmpty() || vagoaneText.isEmpty() || statiiText.isEmpty()) {
                Toast.makeText(this, "Completeaza toate campurile!", Toast.LENGTH_SHORT).show();
                return;
            }

            int id, nrVagoane, nrStatii;
            try {
                id = Integer.parseInt(idText);
                nrVagoane = Integer.parseInt(vagoaneText);
                nrStatii = Integer.parseInt(statiiText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Valori numerice invalide!", Toast.LENGTH_SHORT).show();
                return;
            }

            String magistrala = magistrala_SP.getSelectedItem().toString();

            String traseu;
            int idSelectat = traseu_RG.getCheckedRadioButtonId();
            if (idSelectat == R.id.suprateran_RB) traseu = "Suprateran";
            else if (idSelectat == R.id.mixt_RB) traseu = "Mixt";
            else traseu = "Subteran";

            Metrou metrou = new Metrou(id, model, nrVagoane, nrStatii, magistrala, traseu,
                    uzura.getRating(), accesibil.isChecked(), aer.isChecked());

            Bundle bundle = new Bundle();
            bundle.putSerializable("metrou", metrou);

            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}