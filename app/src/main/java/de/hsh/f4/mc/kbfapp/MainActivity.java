package de.hsh.f4.mc.kbfapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import static de.hsh.f4.mc.kbfapp.R.*;

//Laurence Brenner

public class MainActivity extends AppCompatActivity {

    SwitchCompat LoginSwitch;
    TextView LoginText;
    Button LoginButtonFahrer, LoginButtonUnternehmer;
    EditText lEmail, lPasswort;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate (Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(layout.activity_main);

        LoginSwitch = findViewById(id.switch2);
        LoginText = findViewById(id.textView3);
        LoginButtonFahrer = findViewById(id.buttonLoginFahrer);
        LoginButtonUnternehmer = findViewById(id.buttonLoginUnternehmer);
        lEmail = findViewById(id.editTextTextLoginEmail);
        lPasswort = findViewById(id.editTextTextLoginPassword);

        fAuth = FirebaseAuth.getInstance();

        //Fahrer / Unternehmer Switch
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


        //Login Fahrer
        LoginButtonFahrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lEmail.getText().toString().trim();
                String passwort = lPasswort.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    lEmail.setError("E-Mail Adresse benötigt");
                    return;
                }

                if (TextUtils.isEmpty(passwort)) {
                    lPasswort.setError("Passwort benötigt");
                    return;
                }

                if (passwort.length() < 6) {
                    lPasswort.setError("Password muss mehr als 6 Zeichen haben");
                }

                // Authenticate User

                fAuth.signInWithEmailAndPassword(email,passwort).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, FahrerStartseite.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Anmeldung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Login Unternehmer
        LoginButtonUnternehmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lEmail.getText().toString().trim();
                String passwort = lPasswort.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    lEmail.setError("E-Mail Adresse benötigt");
                    return;
                }

                if (TextUtils.isEmpty(passwort)) {
                    lPasswort.setError("Passwort benötigt");
                    return;
                }

                if (passwort.length() < 6) {
                    lPasswort.setError("Password muss mehr als 6 Zeichen haben");
                }

                // Authenticate User

                fAuth.signInWithEmailAndPassword(email,passwort).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Anmeldung erfolgreich", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, UnternehmerStartseite.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Anmeldung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }



    //Login Fahrer (zu Mapview) / Zu SidebarTest

    /*
    public void oeffneFahrerStartseite(View view) {
        startActivity(new Intent(MainActivity.this, FahrerStartseite.class));
    }
    */


    // /Laurence Brenner

    //David Medic

    public void oeffneUnternehmerStartseite(View view) {
        startActivity(new Intent(MainActivity.this, UnternehmerStartseite.class));
    }

    public void oeffneRegistrierung(View view) {
        startActivity(new Intent(MainActivity.this, Registrierung.class));
    }


    public void oeffnePasswortAnfrage(View view) {
        startActivity(new Intent(MainActivity.this, PasswortAnfrage.class));
    }
}

//David Medic
