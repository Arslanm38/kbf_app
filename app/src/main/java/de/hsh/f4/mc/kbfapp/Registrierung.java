package de.hsh.f4.mc.kbfapp;


import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//Laurence Brenner

public class Registrierung extends AppCompatActivity {

    EditText rName, rEmail, rPasswort, rPasswortBes, rPlz, rStrasse;
    CheckBox rIsUnternehmer, rIsFahrer;
    Button rButtonReg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);

        rName = findViewById(R.id.editTextTextPersonVornameNachname);
        rStrasse = findViewById(R.id.editTextTextAdresseStraße);
        rPlz = findViewById(R.id.editTextTextAdressePostleitzahl);
        rEmail = findViewById(R.id.editTextTextEmail);
        rPasswort = findViewById(R.id.editTextTextPassword);
        rPasswortBes = findViewById(R.id.editTextTextPasswordBestaetigen);
        rButtonReg = findViewById(R.id.buttonReg);
        rIsFahrer = (CheckBox) findViewById(R.id.checkBoxRegFahrer);
        rIsUnternehmer = (CheckBox) findViewById(R.id.checkBoxRegUnternehmer);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


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
                String passwortBes = rPasswortBes.getText().toString().trim();
                String strasse = rStrasse.getText().toString();
                String plz = rPlz.getText().toString();
                String name = rName.getText().toString();
                Boolean isFahrer = rIsFahrer.isChecked();
                Boolean isUnternehmer = rIsUnternehmer.isChecked();

                if (!(isFahrer || isUnternehmer)) {
                    Toast.makeText(Registrierung.this, "Bitte Kontotyp angeben", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(name)) {
                    rName.setError("Name wird benötigt");
                    return;
                }

                if(TextUtils.isEmpty(email)) {
                    rEmail.setError("E-Mail Adresse benötigt");
                    return;
                }

                if(!(passwort.equals(passwortBes))) {
                    rPasswort.setText(null);
                    rPasswortBes.setText(null);
                    rPasswort.setError("Passwörter stimmen nicht überein");
                    return;
                }

                if(TextUtils.isEmpty(strasse)) {
                    rStrasse.setError("Bitte Straße angeben");
                    return;
                }

                if(plz.length() != 5) {
                    rPlz.setError("Die Postleitzahl muss 5-stellig sein");
                    return;
                }

                if(TextUtils.isEmpty(passwort)) {
                    rPasswort.setError("Passwort benötigt");
                    return;
                }

                if(passwort.length() < 6) {
                    rPasswort.setError("Password muss mehr als 6 Zeichen haben");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,passwort).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Registrierung.this, "Konto erfolgreich erstellt", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);


                            Map<String, Object> user = new HashMap<>();

                            user.put("NAME", name);
                            user.put("PLZ", plz);
                            user.put("STRASSE", strasse);
                            user.put("EMAIL", email);
                            user.put("ISTUNTERNEHMER", isUnternehmer);
                            user.put("ISTFAHRER", isFahrer);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: user Profil erstellt für " + userID);
                                }

                            });
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