package de.hsh.f4.mc.kbfapp;

public class UserData {

    private String UID;

    private String NAME;
    private String PLZ;
    private String STRASSE;
    private String EMAIL;
    private String UNTERNEHMEN;
    private String UNTERNEHMENNUMMER;

    private boolean ISTUNTERNEHMER;
    private boolean ISTFAHRER;

    public UserData() {}

    public UserData(String NAME, String PLZ,  String STRASSE,  String EMAIL,  String UNTERNEHMEN,  String UNTERNEHMENNUMMER, boolean ISTUNTERNEHMER, boolean ISTFAHRER) {
        this.NAME = NAME;
        this.PLZ = PLZ;
        this.STRASSE = STRASSE;
        this.EMAIL = EMAIL;
        this.UNTERNEHMEN = UNTERNEHMEN;
        this.UNTERNEHMENNUMMER = UNTERNEHMENNUMMER;
        this.ISTFAHRER = ISTFAHRER;
        this.ISTUNTERNEHMER = ISTUNTERNEHMER;
    }

    public String getName() {
        return NAME;
    }

    public String getPLZ() {
        return PLZ;
    }

    public String getStrasse() {
        return STRASSE;
    }

    public String getEmail() {
        return EMAIL;
    }

    public String getUnternehmen() {
        return UNTERNEHMEN;
    }

    public String getUnternehmenNummer() {
        return UNTERNEHMENNUMMER;
    }

    public boolean getIstUnternehmer() {
        return ISTUNTERNEHMER;
    }

    public boolean getIstFahrer() {
        return ISTFAHRER;
    }
}
