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

public class UnternehmerFahrten extends AppCompatActivity {

    String uid, uUnternehmen;
    UserData currentUser;
    String fUid, fPreis, fName, fData;
    private String TAG;
    private TextView uFahrten;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unternehmer_fahrten);

        uFahrten = findViewById(R.id.textViewUFahrten);

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

                                        fUid = fahrtenListe.getFAHRERUID().trim();
                                        fPreis = fahrtenListe.getPREIS();

                                        getFahrerName(fUid);

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

    public void getFahrerName(String fahrerID) {
        DocumentReference fahrerRef = db.collection("users").document(fahrerID);
        fahrerRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(UserData.class);
                fName = currentUser.getName();

                fData = "Fahrername: " + fName + "\n" + "Preis: " + fPreis + "\n\n";

                uFahrten.setText(fData);
            }
        });
    }
}

/*









 */