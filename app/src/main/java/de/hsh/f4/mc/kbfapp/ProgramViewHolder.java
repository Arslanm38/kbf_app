package de.hsh.f4.mc.kbfapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {

    ImageView itemImage;
    TextView programFahrtnr;
    TextView programName;
    TextView programDatum;

    ProgramViewHolder (View v)
    {
        itemImage = v.findViewById(R.id.imageView);
        programFahrtnr = v.findViewById(R.id.textView1);
        programName = v.findViewById(R.id.textView2);
        programDatum = v.findViewById(R.id.textView3);

    }

}
