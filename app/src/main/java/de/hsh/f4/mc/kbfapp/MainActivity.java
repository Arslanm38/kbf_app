package de.hsh.f4.mc.kbfapp;
/* Erstellt von David Medic*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import static de.hsh.f4.mc.kbfapp.R.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout.activity_main);
    }

    public void oeffneRegistrierung(View view) {
        startActivity(new Intent(MainActivity.this, Registrierung.class));
    }

    public void oeffneUnternehmerStartseite(View view) {
        startActivity(new Intent(MainActivity.this, UnternehmerStartseite.class));
    }

    public void wechsleFahrerLogin(View view) {
        startActivity(new Intent(MainActivity.this, FahrerLogin.class));
    }

}
/* Erstellt von David Medic*/