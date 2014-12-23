package parser;

public class NordeaEvent {

    private static final int FIELD_KIRJAUSPAIVA = 0;
    private static final int FIELD_ARVOPAIVA = 1;
    private static final int FIELD_MAKSUPAIVA = 2;
    private static final int FIELD_MAARA = 3;
    private static final int FIELD_SAAJA_OR_MAKSAJA = 4;
    private static final int FIELD_TILINUMERO = 5;
    private static final int FIELD_BIC = 6;
    private static final int FIELD_TAPAHTUMA = 7;
    private static final int FIELD_VIITE = 8;
    private static final int FIELD_MAKSAJAN_VIITE = 9;
    private static final int FIELD_KORTINNUMERO = 10;
    private static final int FIELD_KUITTI = 11;

    private String rawLine;
    private String kirjauspäivä;
    private String arvopäivä;
    private String maksupäivä;
    private String määrä;
    private String saajaTaiMaksaja;
    private String tilinumero;
    private String bic;
    private String tapahtuma;
    private String viite;
    private String maksajanViite;
    private String kortinnumero;
    private String kuitti;

    public NordeaEvent(String rawLine) {
        this.rawLine = rawLine;

        String[] splitted = rawLine.split("\t");

        if (splitted.length > 0)  { this.kirjauspäivä = splitted[0]; }
        if (splitted.length > 1)  { this.arvopäivä = splitted[1]; }
        if (splitted.length > 2)  { this.maksupäivä = splitted[2]; }

        if (splitted.length > 3)  { this.määrä = splitted[3]; }
        if (splitted.length > 4)  { this.saajaTaiMaksaja = splitted[4]; }
        if (splitted.length > 5)  { this.tilinumero = splitted[5]; }

        if (splitted.length > 6)  { this.bic = splitted[6]; }
        if (splitted.length > 7)  { this.tapahtuma = splitted[7]; }
        if (splitted.length > 8)  { this.viite = splitted[8]; }

        if (splitted.length > 9)  { this.maksajanViite = splitted[9]; }
        if (splitted.length > 10) { this.kortinnumero = splitted[10]; }
        if (splitted.length > 11) { this.kuitti = splitted[11]; }
    }

}
