package de.hsh.f4.mc.kbfapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;


/* Erstellt von David Medic*/

public class FahrerLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fahrer_login);
    }

    public void oeffneRegistrierung(View view) {
        startActivity(new Intent(FahrerLogin.this, Registrierung.class));
    }

    public void oeffneUnternehmerStartseite(View view) {
        startActivity(new Intent(FahrerLogin.this, UnternehmerStartseite.class));
    }

    // Laurence Brenner
    public void oeffneFahrerStartseite(View view) {
        startActivity(new Intent(FahrerLogin.this, FahrerStartseite.class));
    }

    public void wechsleUnternehmerLogin(View view) {
        finish();
    }

    /* /Erstellt von David Medic*/


}
