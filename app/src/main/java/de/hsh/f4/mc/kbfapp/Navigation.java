package de.hsh.f4.mc.kbfapp;
/*erstellt von David Medic*/
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Navigation extends AppCompatActivity {
    //Initialiserung
    EditText etStartpunkt, etZielpunkt;
    Button button_route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_navigation);

        etStartpunkt = findViewById(R.id.et_startpunkt);
        etZielpunkt = findViewById(R.id.et_zielpunkt);
        button_route = findViewById(R.id.button_route);

        button_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sStartpunkt = etStartpunkt.getText().toString().trim();
                String sZielpunkt = etZielpunkt.getText().toString().trim();

                if (sStartpunkt.equals("") && sZielpunkt.equals("")) {

                    Toast.makeText(getApplicationContext()
                            , "Bitte geben Sie Start- und Zielpunkt.", Toast.LENGTH_SHORT).show();
                } else {
                    DisplayTrack(sStartpunkt, sZielpunkt);


                }

            }
        });
    }


    private void DisplayTrack(String sStartpunkt, String sZielpunkt) {
        //Wenn kein Map installiert ist, soll es zu Play Store übertragen werden

        try {
            //Wenn Google Map installiert ist
            Uri uri = Uri.parse("https://www.google.de/maps/dir/" + sStartpunkt + "/" + sZielpunkt);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            intent.setPackage("com.google.android.apps.maps");

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

        }catch (ActivityNotFoundException e) {
            //Wenn google maps nicht installiert wurde
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

        }

    }
}

/*erstellt von David Medic*/