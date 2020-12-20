package de.hsh.f4.mc.kbfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LetztenFahrten extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_letzten_fahrten);
    }

    public void oeffneMeineFahrten(View view) {
        startActivity(new Intent( LetztenFahrten.this, MeineFahrten.class));
    }


}