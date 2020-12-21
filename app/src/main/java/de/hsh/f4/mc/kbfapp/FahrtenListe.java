package de.hsh.f4.mc.kbfapp;

// Laurence Brenner

import com.google.firebase.Timestamp;

public class FahrtenListe {

    private String FAHRERUID;
    private String PREIS;
    private Timestamp FAHRTANTRITT;

    public FahrtenListe() {

    }

    public FahrtenListe(String FAHRERUID, String PREIS, Timestamp FAHRTANTRITT) {
        this.FAHRERUID = FAHRERUID;
        this.PREIS = PREIS;
        this.FAHRTANTRITT = FAHRTANTRITT;
    }

    public String getFAHRERUID() {
        return FAHRERUID;
    }

    public String getPREIS() {
        return PREIS;
    }

    public Timestamp getFAHRTANTRITT() {
        return FAHRTANTRITT;
    }
}
