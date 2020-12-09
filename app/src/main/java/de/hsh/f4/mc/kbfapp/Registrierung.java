package de.hsh.f4.mc.kbfapp;


import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//Laurence Brenner

public class Registrierung extends AppCompatActivity {

    EditText rName, rEmail, rPasswort, rPasswortBes;
    Button rButtonReg;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);

        rName = findViewById(R.id.editTextTextPersonVornameNachname);
        rEmail = findViewById(R.id.editTextTextEmail);
        rPasswort = findViewById(R.id.editTextTextPassword);
        rPasswortBes = findViewById(R.id.editTextTextPasswordBestaetigen);
        rButtonReg = findViewById(R.id.buttonReg);

        fAuth = FirebaseAuth.getInstance();


        // Weiterleitung für angemeldete User

                                        /*
                                        if(fAuth.getCurrentUser() != null) {
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            finish();
                                        }
                                        */

        // Registrierung
        rButtonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString().trim();
                String passwort = rPasswort.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    rEmail.setError("E-Mail Adresse benötigt");
                    return;
                }

                if(TextUtils.isEmpty(passwort)) {
                    rPasswort.setError("Passwort benötigt");
                    return;
                }

                if(passwort.length() < 6) {
                    rPasswort.setError("Password muss mehr als 6 Zeichen haben");
                }

                fAuth.createUserWithEmailAndPassword(email,passwort).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Registrierung.this, "Konto erfolgreich erstellt", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(Registrierung.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

// /Laurence Brenner


    /* Erstellt von David Medic*/


    public void onLogin(View view) {
        finish();
    }

}

/* Erstellt von David Medic*/