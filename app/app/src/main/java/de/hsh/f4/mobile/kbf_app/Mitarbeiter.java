package de.hsh.f4.mobile.kbf_app;
/* Erstellt von David Medic*/

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mitarbeiter extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitarbeiter);

        ListView listView = (ListView) findViewById(R.id.listview);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");
        arrayList.add("Max Mustermann");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Mitarbeiter.this, "clicked item:" + i + " " + arrayList.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

/* Erstellt von David Medic*/