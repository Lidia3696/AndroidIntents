package com.example.conversorunitatslidiaingles;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class AlarmaIntent extends AppCompatActivity {

    //hora i minuts
    int hora, minuts;
    //missatge que te l'alarma
    String missatge;

    TextInputEditText horaInput, minutInput, missatgeInput;
    Button crearAlarmaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alarma_intent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    //declarar inputs
    horaInput = findViewById(R.id.inputHora);
    minutInput = findViewById(R.id.inputMinut);
    missatgeInput = findViewById(R.id.inputMissatge);
    crearAlarmaButton = findViewById(R.id.bttSetAlarm);

    crearAlarmaButton.setOnClickListener(v -> getValues());
    }

    //agafar valors dels textinputs
    public void getValues() {
        //comprovar que els inputs no estiguin buits
        //un toast es un missatge petit que surt a la part inferior de la pantalla
        String horaStr = horaInput.getText().toString().trim();
        String minutStr = minutInput.getText().toString().trim();
        missatge = missatgeInput.getText().toString().trim();

        if (horaStr.isEmpty() || minutStr.isEmpty() || missatge.isEmpty()) {
            Toast.makeText(this, "Completa tots els camps", Toast.LENGTH_SHORT).show();
            return;
        }


        //comprovar que els numeros e pguin parsejar be (que no hagin escrites lletres)
        try {
            missatge = missatgeInput.getText().toString();
            hora = Integer.parseInt(horaInput.getText().toString());
            minuts = Integer.parseInt(minutInput.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Escriu valors válids", Toast.LENGTH_SHORT).show();
            return;
        }

        crearAlarma(missatge, hora, minuts);

    }

    public void crearAlarma(String message, int hour, int minutes) {

        try {
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                    .putExtra(AlarmClock.EXTRA_HOUR, hour)
                    .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

    }
}