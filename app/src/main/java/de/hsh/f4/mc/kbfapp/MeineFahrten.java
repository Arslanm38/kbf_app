package de.hsh.f4.mc.kbfapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MeineFahrten extends AppCompatActivity {



    ListView lvProgram;
    String [] programFahrtnr = { "Fahrt ID : 1234873" , "Fahrt ID : 1234873" ,"Fahrt ID : 1234873" ,"Fahrt ID : 1234873" ,"Fahrt ID : 1234873" ,"Fahrt ID : 1234873" ,"Fahrt ID : 1234873","Fahrt ID : 1234873","Fahrt ID : 1234873","Fahrt ID : 1234873","Fahrt ID : 1234873","Fahrt ID : 1234873","Fahrt ID : 1234873","Fahrt ID : 1234873"  };
    String [] programName = { "Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt","Schmitt"};
    String [] programDatum = {"18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 ","18.06.2020    13:45 "};
    int[] programImages = {R.drawable.checkt, R.drawable.ic_done, R.drawable.ic_done, R.drawable.checkt, R.drawable.checkt, R.drawable.checkt, R.drawable.checkt,R.drawable.ic_done,R.drawable.ic_done,R.drawable.ic_done,R.drawable.ic_done,R.drawable.ic_done,R.drawable.ic_done,R.drawable.ic_done,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meine_fahrten);

        lvProgram = findViewById(R.id.lvProgram);
        ProgramAdapter programAdapter = new ProgramAdapter (this, programFahrtnr, programImages, programName, programDatum);
        lvProgram.setAdapter(programAdapter);
    }
}
