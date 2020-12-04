package de.hsh.f4.mobile.kbf_app;
/* Erstellt von David Medic*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

    public void wechsleUnternehmerLogin(View view) {
        finish();

    }
}

/* Erstellt von David Medic*/