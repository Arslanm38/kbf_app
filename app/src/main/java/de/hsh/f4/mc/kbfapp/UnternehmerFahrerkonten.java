package de.hsh.f4.mc.kbfapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Laurence Brenner und David Medic

public class UnternehmerFahrerkonten extends AppCompatActivity {

    EditText rName, rEmail, rStrasse, rPlz, rUnternehmennummer;
    String userID, uid, uUnternehmen;
    Button uReg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    UserData currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unternehmer_fahrerkonten);

        // Nutzer-ID abrufen
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) uid = user.getUid();

        rName = findViewById(R.id.editTextTextPersonVornameNachname);
        rStrasse = findViewById(R.id.editTextTextAdresseStraße);
        rPlz = findViewById(R.id.editTextTextAdressePostleitzahl);
        rUnternehmennummer = findViewById(R.id.editTextTextUnternehmennummer);
        rEmail = findViewById(R.id.editTextTextEmail);
        uReg = findViewById(R.id.buttonUReg);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        uReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString().trim();
                String strasse = rStrasse.getText().toString();
                String plz = rPlz.getText().toString();
                String unternehmennummer = rUnternehmennummer.getText().toString();
                String name = rName.getText().toString();
                String passwort = "qwjM4WdTd1jKZAO0cYq";

                if (TextUtils.isEmpty(name)) {
                    rName.setError("Name wird benötigt");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    rEmail.setError("E-Mail Adresse benötigt");
                    return;
                }

                if (TextUtils.isEmpty(strasse)) {
                    rStrasse.setError("Bitte Straße angeben");
                    return;
                }

                if (plz.length() != 5) {
                    rPlz.setError("Die Postleitzahl muss 5-stellig sein");
                    return;
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) uid = user.getUid();

                // Unternehmen des Nutzers abrufen
                DocumentReference docRef = db.collection("users").document(uid);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                  @Override
                  public void onSuccess(DocumentSnapshot documentSnapshot) {
                      currentUser = documentSnapshot.toObject(UserData.class);
                      assert currentUser != null;
                      uUnternehmen = currentUser.getUnternehmen();
                  }
                });


                fAuth.createUserWithEmailAndPassword(email,passwort).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(UnternehmerFahrerkonten.this, "Konto erfolgreich erstellt", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);


                            Map<String, Object> user = new HashMap<>();

                            user.put("NAME", name);
                            user.put("PLZ", plz);
                            user.put("STRASSE", strasse);
                            user.put("EMAIL", email);
                            user.put("UNTERNEHMEN", uUnternehmen);
                            user.put("UNTERNEHMENNUMMER", unternehmennummer);
                            user.put("ISTUNTERNEHMER", false);
                            user.put("ISTFAHRER", true);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: user Profil erstellt für " + userID);
                                }

                            });
                        }


                        else {
                            Toast.makeText(UnternehmerFahrerkonten.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                rName.setText("");
                rStrasse.setText("");
                rPlz.setText("");
                rUnternehmennummer.setText("");
                rEmail.setText("");


            }
        });
    }
}