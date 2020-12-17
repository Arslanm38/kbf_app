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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_mitarbeiter);

        editTextName = findViewById(R.id.editTextName);
        editTextUnternehmen = findViewById(id.editTextUnternehmen);
        textViewName = findViewById(R.id.textViewName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        usersRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    return;
                }

                String data = "";

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    MitarbeiterListe mitarbeiterListe = documentSnapshot.toObject(MitarbeiterListe.class);

                    String name = mitarbeiterListe.getName();
                    String unternehmen = mitarbeiterListe.getUnternehmen();

                    data += "Name:" + name + "\n" + "Unternehmen:" + unternehmen + "\n\n";
                }

                textViewName.setText(data);
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

    public void ladeMitarbeiter(View v) {

        usersRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            MitarbeiterListe mitarbeiterListe = documentSnapshot.toObject(MitarbeiterListe.class);

                            String name = mitarbeiterListe.getName();
                            String unternehmen = mitarbeiterListe.getUnternehmen();

                            data += "Name:" + name + "\n" + "Unternehmen:" + unternehmen + "\n\n";


                        }

                        textViewName.setText(data);

                    }
                });


    }
}

/* Erstellt von David Medic*/