package de.hsh.f4.mc.kbfapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import static de.hsh.f4.mc.kbfapp.R.*;

// Laurence Brenner

public class MainActivity extends AppCompatActivity {

    SwitchCompat LoginSwitch;
    TextView LoginText;
    Button LoginButtonFahrer, LoginButtonUnternehmer;
    EditText lEmail, lPasswort;
    FirebaseAuth fAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate (Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(layout.activity_main);

        LoginSwitch = findViewById(id.switchLogin);
        LoginText = findViewById(id.textViewLoginTyp);
        LoginButtonFahrer = findViewById(id.buttonLoginFahrer);
        LoginButtonUnternehmer = findViewById(id.buttonLoginUnternehmer);
        lEmail = findViewById(id.editTextTextLoginEmail);
        lPasswort = findViewById(id.editTextTextLoginPassword);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        // Fahrer / Unternehmer Switch
        LoginSwitch.setOnClickListener(v -> {
            if (LoginSwitch.isChecked()) {
                LoginText.setText(string.LoginUnternehmerLogin);
                LoginSwitch.setText(string.LoginFürUnternehmer);
                LoginButtonFahrer.setVisibility(View.INVISIBLE);
                LoginButtonUnternehmer.setVisibility(View.VISIBLE);
            }

            else {
                LoginText.setText(string.LoginFahrerLogin);
                LoginSwitch.setText(string.LoginFürUnternehmer);
                LoginButtonFahrer.setVisibility(View.VISIBLE);
                LoginButtonUnternehmer.setVisibility(View.INVISIBLE);
            }
        });


        // Login Fahrer
        LoginButtonFahrer.setOnClickListener(v -> {
            String email = lEmail.getText().toString().trim();
            String passwort = lPasswort.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                lEmail.setError("E-Mail Adresse benötigt");
                return;
            }

            if (TextUtils.isEmpty(passwort)) {
                lPasswort.setError("Passwort benötigt");
                return;
            }

            if (passwort.length() < 6) {
                lPasswort.setError("Password muss mehr als 6 Zeichen haben");
            }

            // Authenticate User
            fAuth.signInWithEmailAndPassword(email,passwort).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, FahrerStartseite.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Anmeldung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                }
            });
        });


        // Login Unternehmer
        LoginButtonUnternehmer.setOnClickListener(v -> {
            String email = lEmail.getText().toString().trim();
            String passwort = lPasswort.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                lEmail.setError("E-Mail Adresse benötigt");
                return;
            }

            if (TextUtils.isEmpty(passwort)) {
                lPasswort.setError("Passwort benötigt");
                return;
            }

            if (passwort.length() < 6) {
                lPasswort.setError("Password muss mehr als 6 Zeichen haben");
            }

            // Authenticate User
            fAuth.signInWithEmailAndPassword(email,passwort).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, UnternehmerStartseite.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Anmeldung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // /Laurence Brenner

    //David Medic

    public void oeffneUnternehmerStartseite(View view) {
        startActivity(new Intent(MainActivity.this, UnternehmerStartseite.class));
    }

    public void oeffneRegistrierung(View view) {
        startActivity(new Intent(MainActivity.this, Registrierung.class));
    }


    public void oeffnePasswortAnfrage(View view) {
        startActivity(new Intent(MainActivity.this, PasswortAnfrage.class));
    }
}

//David Medic
