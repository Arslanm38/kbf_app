package de.hsh.f4.mc.kbfapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UnternehmerFahrten extends AppCompatActivity {

    String uid, uUnternehmen;
    UserData currentUser;
    Timestamp tfZeit;
    String fData;
    private String TAG;
    private TextView uFahrten;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unternehmer_fahrten);

        uFahrten = findViewById(R.id.textViewUFahrten);
        fData = "";

        // Nutzer-ID abrufen
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

                db.collection("fahrten")
                        .whereEqualTo("UNTERNEHMEN", uUnternehmen)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        FahrtenListe fahrtenListe = document.toObject(FahrtenListe.class);

                                        String fUid = fahrtenListe.getFAHRERUID().trim();
                                        String fPreis = fahrtenListe.getPREIS();
                                        Timestamp tfZeit = fahrtenListe.getFAHRTANTRITT();
                                        Date dZeit = new Date();
                                        dZeit = tfZeit.toDate();

                                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm - dd.MM.yyyy");
                                        String fZeit = dateFormat.format(dZeit);

                                        getFahrerName(fUid, fPreis, fZeit);

                                    }
                                }

                                else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });
    }

    public void getFahrerName(String fahrerID, String fPreis, String fZeit) {
        DocumentReference fahrerRef = db.collection("users").document(fahrerID);
        fahrerRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(UserData.class);
                String fName = currentUser.getName();
                String fUntNmr = currentUser.getUnternehmenNummer();

                fData += "Fahrername: " + fName + " - " + fUntNmr + "\n" + "Preis: " + fPreis + "€ |  Uhrzeit: " + fZeit + "\n\n";

                uFahrten.setText(fData);
            }
        });
    }
}
