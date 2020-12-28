package de.hsh.f4.mc.kbfapp;

import com.google.firebase.firestore.GeoPoint;

public class KbfSchein {

    private String ZIELLAT;
    private String ZIELLNG;

    public KbfSchein() {
    }

    public KbfSchein(String ZIELLAT, String ZIELLNG) {
        this.ZIELLAT = ZIELLAT;
        this.ZIELLNG = ZIELLNG;
    }

    public String getZIELLAT() {
        return ZIELLAT;
    }

    public String getZIELLNG() {
        return ZIELLNG;
    }
}
