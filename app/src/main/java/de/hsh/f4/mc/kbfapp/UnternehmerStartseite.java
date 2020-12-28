package de.hsh.f4.mc.kbfapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static de.hsh.f4.mc.kbfapp.R.layout.activity_unternehmer_startseite;

/* Erstellt von David Medic */
public class UnternehmerStartseite extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uid;
    UserData currentUser;
    public boolean istUnternehmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(activity_unternehmer_startseite);

        //Nur Unternehmer haben Zugriff auf die Unternehmerstartseite (Überprüfung, ob  man der Unternehmer ist)

        FirebaseUser fahrer = FirebaseAuth.getInstance().getCurrentUser();
        if (fahrer != null) uid = fahrer.getUid();

        DocumentReference docRefs = db.collection("users").document(uid);
        docRefs.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(UserData.class);
                istUnternehmer = currentUser.getIstUnternehmer();

                if (istUnternehmer != false) {

                    Toast.makeText(UnternehmerStartseite.this, "Erfolgreich als Fahrer angemeldet", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UnternehmerStartseite.this, "Sie sind kein Unternehmer", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UnternehmerStartseite.this, MainActivity.class));
                }
            }
        });

    }

    public void oeffneMitarbeiter(View view) {
        startActivity(new Intent(UnternehmerStartseite.this, Mitarbeiter.class));
    }

    public void oeffneHilfe(View view) {
        startActivity(new Intent(UnternehmerStartseite.this, HilfeFragment.class));
    }

    public void oeffneKontakt(View view)  {
        startActivity(new Intent(UnternehmerStartseite.this, KontaktFragment.class));
    }

    public void oeffneEinstellungen(View view)  {
        startActivity(new Intent(UnternehmerStartseite.this, Einstellungen.class));
    }

    // Laurence
    public void oeffneFahrten(View view)  {
        startActivity(new Intent(UnternehmerStartseite.this, UnternehmerFahrten.class));
    }

    public void oeffneFahrerkonten(View view)   {
        startActivity(new Intent(UnternehmerStartseite.this, UnternehmerFahrerkonten.class));
    }

    // Arslan
    public void oeffneMeineFahrten(View view) {
        startActivity(new Intent(UnternehmerStartseite.this, MeineFahrten.class));
    }
}


/* Erstellt von David Medic */