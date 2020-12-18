package de.hsh.f4.mc.kbfapp;
/* Erstellt von David Medic*/

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static de.hsh.f4.mc.kbfapp.R.*;

public class Mitarbeiter extends AppCompatActivity {

    private static final String TAG = "Mitarbeiter";

    private static final String KEY_USER = "name";
    private static final String KEY_UNTERNEHMEN = "unternehmen";


    private EditText editTextName;
    private EditText editTextUnternehmen;
    private TextView textViewName;
    UserData currentUser;
    String uid;
    String fUnternehmen;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_mitarbeiter);

        editTextName = findViewById(R.id.editTextName);
        editTextUnternehmen = findViewById(id.editTextUnternehmen);
        textViewName = findViewById(R.id.textViewName);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) uid = user.getUid();

        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(UserData.class);
                fUnternehmen = currentUser.getUnternehmen();

                // Laurence Brenner

                db.collection("users")
                        .whereEqualTo("UNTERNEHMEN", fUnternehmen)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                String data = "";
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        MitarbeiterListe mitarbeiterListe2 = document.toObject(MitarbeiterListe.class);


                                        String name = mitarbeiterListe2.getName();
                                        String unternehmen = mitarbeiterListe2.getUnternehmen();

                                        data += "Name:" + name + "\n" + "Unternehmen:" + unternehmen + "\n\n";
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }

                                textViewName.setText(data);
                            }
                        });

                // /Laurence Brenner
            }
        });

    }

    public void hinzufuegeMitarbeiter(View v) {
        String NAME = editTextName.getText().toString();
        String UNTERNEHMEN = editTextUnternehmen.getText().toString();

        Map<String, Object> mitarbeiterListe = new HashMap<>();

        mitarbeiterListe.put("NAME", NAME);
        mitarbeiterListe.put("UNTERNEHMEN", UNTERNEHMEN);

        usersRef.add(mitarbeiterListe);

    }

    // Laurence Brenner

    public void ladeMitarbeiter(View v) {

        db.collection("users")
                .whereEqualTo("UNTERNEHMEN", fUnternehmen)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String data = "";
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MitarbeiterListe mitarbeiterListe2 = document.toObject(MitarbeiterListe.class);


                                String name = mitarbeiterListe2.getName();
                                String unternehmen = mitarbeiterListe2.getUnternehmen();

                                data += "Name:" + name + "\n" + "Unternehmen:" + unternehmen + "\n\n";
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        textViewName.setText(data);
                    }
                });
    }
}

/* Erstellt von David Medic*/