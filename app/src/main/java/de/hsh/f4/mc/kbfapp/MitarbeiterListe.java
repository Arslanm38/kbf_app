package de.hsh.f4.mc.kbfapp;

/* Erstellt von David Medic*/

public class MitarbeiterListe {

    private String NAME;
    private String UNTERNEHMEN;

    public MitarbeiterListe() {
        //Public kein Konstruktor
    }


    public MitarbeiterListe(String NAME, String UNTERNEHMEN) {
        this.NAME = NAME;
        this.UNTERNEHMEN = UNTERNEHMEN;
    }

    public String getName() {
        return NAME;
    }

    public String getUnternehmen() {
        return UNTERNEHMEN;
    }
}

/* Erstellt von David Medic*/
