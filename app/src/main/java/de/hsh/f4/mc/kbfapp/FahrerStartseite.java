/*David Medic*/

package de.hsh.f4.mc.kbfapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static de.hsh.f4.mc.kbfapp.R.id.navHostFragment;

public class FahrerStartseite extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String uid;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");
    UserData currentUser;
    String fName, fUnternehmen, fUnternehmenNummer;
    TextView textViewfName, textViewfUnternehmen, textViewfUnternehmenNummer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahrer_startseite);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) uid = user.getUid();

        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(UserData.class);
                fName = currentUser.getName();
                fUnternehmen = currentUser.getUnternehmen();
                fUnternehmenNummer = currentUser.getUnternehmenNummer();

                textViewfName = (TextView) findViewById(R.id.textViewfName);
                textViewfUnternehmen = (TextView) findViewById(R.id.textViewfUnternehmen);
                textViewfUnternehmenNummer = (TextView) findViewById(R.id.textViewfUnternehmenNummer);

                fUnternehmenNummer = "#"+fUnternehmenNummer;

                textViewfName.setText(fName);
                textViewfUnternehmen.setText(fUnternehmen);
                textViewfUnternehmenNummer.setText("#" + fUnternehmenNummer);
            }
        });

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);


        NavController navController = Navigation.findNavController(this, getNavHostFragment());
        NavigationUI.setupWithNavController(navigationView, navController);

        final TextView textTitle = findViewById(R.id.textTitle);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());
            }
        });

    }







    private int getNavHostFragment() {
        return navHostFragment;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng hannoverHbf = new LatLng(52.375, 9.739);
        mMap.addMarker(new MarkerOptions().position(hannoverHbf).title("Hannover Hauptbahnhof"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hannoverHbf));
    }
}
