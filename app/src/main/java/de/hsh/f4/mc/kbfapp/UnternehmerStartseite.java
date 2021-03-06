package de.hsh.f4.mc.kbfapp;
/* Erstellt von David Medic*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import static de.hsh.f4.mc.kbfapp.R.layout.*;

public class UnternehmerStartseite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(activity_unternehmer_startseite);
    }

    public void oeffneMitarbeiter(View view) {
        startActivity(new Intent(UnternehmerStartseite.this, Mitarbeiter.class));
    }

    public void oeffneHilfe(View view) {
        startActivity(new Intent(UnternehmerStartseite.this, HilfeFragment.class));
    }

    public void oeffneKontakt(View view)  {
        startActivity(new Intent(UnternehmerStartseite.this, KontaktFragment.class));
    }
}

/* Erstellt von David Medic*/