package de.hsh.f4.mc.kbfapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;



public class ProgramAdapter extends ArrayAdapter <String>{

    Context context;
    int[] images;
    String[] programFahrtnr;
    String[] programName;
    String[] programDatum;


    public ProgramAdapter( Context context, String[] programFahrtnr, int[] images, String[] programName, String[] programDatum) {
        super(context, R.layout.row, R.id.textView1, programFahrtnr);
        this.context = context;
        this.images = images;
        this.programFahrtnr = programFahrtnr;
        this.programName = programName;
        this.programDatum= programDatum;

    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        View row = convertView;
        ProgramViewHolder holder = null;
        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row, parent, false);
            holder = new ProgramViewHolder(row);
            row.setTag(holder);


        }
        else {
            holder = (ProgramViewHolder) row.getTag();
        }







        return super.getView (position, convertView, parent);
    }
}
