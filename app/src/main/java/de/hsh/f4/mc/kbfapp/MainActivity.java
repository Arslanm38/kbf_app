package de.hsh.f4.mc.kbfapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import org.w3c.dom.Text;

import static de.hsh.f4.mc.kbfapp.R.*;

//Laurence Brenner

public class MainActivity extends AppCompatActivity {

    SwitchCompat LoginSwitch;
    TextView LoginText;
    Button LoginButtonFahrer, LoginButtonUnternehmer;

    @Override
    protected void onCreate (Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(layout.activity_main);

        LoginSwitch = findViewById(id.switch2);
        LoginText = findViewById(id.textView3);
        LoginButtonFahrer = findViewById(id.buttonLoginFahrer);
        LoginButtonUnternehmer = findViewById(id.buttonLoginUnternehmer);

        LoginSwitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (LoginSwitch.isChecked()) {
                    LoginText.setText("Unternehmer-Login");
                    LoginSwitch.setText("Für Fahrer");
                    LoginButtonFahrer.setVisibility(View.INVISIBLE);
                    LoginButtonUnternehmer.setVisibility(View.VISIBLE);


                }

                else {
                    LoginText.setText("Fahrer-Login");
                    LoginSwitch.setText("Für Unternehmer");
                    LoginButtonFahrer.setVisibility(View.VISIBLE);
                    LoginButtonUnternehmer.setVisibility(View.INVISIBLE);


                }
            }
        });
    }

    public void oeffneFahrerStartseite(View view) {
        startActivity(new Intent(MainActivity.this, FahrerStartseite.class));
    }

    public void oeffneSidebarTest (View view) {
        startActivity(new Intent(MainActivity.this, SidebarTest.class));
    }

    //David Medic

    public void oeffneUnternehmerStartseite(View view) {
        startActivity(new Intent(MainActivity.this, UnternehmerStartseite.class));
    }

    public void oeffneRegistrierung(View view) {
        startActivity(new Intent(MainActivity.this, Registrierung.class));
    }
}
