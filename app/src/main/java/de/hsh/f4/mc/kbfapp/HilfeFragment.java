/* Erstellt von David Medic*/

package de.hsh.f4.mc.kbfapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HilfeFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hilfe);
    }

    public void oeffneAGB(View view) {
        startActivity(new Intent(HilfeFragment.this, HilfeFragmentAGB.class));
    }


    public void oeffneHilfeDauer(View view) {
        startActivity(new Intent(HilfeFragment.this, HilfeFragmentDauer.class));
    }

    public void oeffneHilfeKonto(View view) {
        startActivity(new Intent(HilfeFragment.this, HilfeFragmentKonto.class));
    }

    public void oeffneHilfeStandort(View view) {
        startActivity(new Intent(HilfeFragment.this, HilfeFragmentStandort.class));
    }

    public void oeffneHilfeZahlung(View view) {
        startActivity(new Intent(HilfeFragment.this, HilfeFragmentZahlung.class));
    }
}



/* Erstellt von David Medic*/