package de.hsh.f4.mc.kbfapp;
/* Erstellt von David Medic*/

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Registrierung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);
    }

    public void onLogin(View view) {
        finish();
    }
    public void onRegister(View view) {
        finish();
    }
    
}

/* Erstellt von David Medic*/