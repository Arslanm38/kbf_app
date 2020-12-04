package de.hsh.f4.mobile.kbf_app;
/* Erstellt von David Medic*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class UnternehmerStartseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_unternehmer_startseite);
    }

    public void oeffneMitarbeiter(View view) {
        startActivity(new Intent(UnternehmerStartseite.this, Mitarbeiter.class));
    }
}

/* Erstellt von David Medic*/