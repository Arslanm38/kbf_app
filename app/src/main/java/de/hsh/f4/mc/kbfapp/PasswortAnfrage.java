//David Medic

package de.hsh.f4.mc.kbfapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswortAnfrage extends AppCompatActivity {
    private EditText userEmail;
    private Button sendEmail;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_passwort_anfrage);
        userEmail = findViewById(R.id.email_angabe);
        sendEmail = findViewById(R.id.sende_email);
        mAuth = FirebaseAuth.getInstance();
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(PasswortAnfrage.this, "E-Mail Adresse angeben", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PasswortAnfrage.this, "E-Mail Bestätigungslink wurde verschickt.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PasswortAnfrage.this, MainActivity.class));
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(PasswortAnfrage.this, "Fehler:" + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

//David Medic