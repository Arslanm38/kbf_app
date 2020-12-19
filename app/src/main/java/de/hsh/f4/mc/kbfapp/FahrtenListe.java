package de.hsh.f4.mc.kbfapp;

// Laurence Brenner

import com.google.firebase.Timestamp;

public class FahrtenListe {

    private String FAHRERUID;
    private String PREIS;

    public FahrtenListe() {

    }

    public FahrtenListe(String FAHRERUID, String PREIS) {
        this.FAHRERUID = FAHRERUID;
        this.PREIS = PREIS;
    }

    public String getFAHRERUID() {
        return FAHRERUID;
    }

    public String getPREIS() {
        return PREIS;
    }

}
