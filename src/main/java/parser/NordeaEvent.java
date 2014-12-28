package parser;

public class NordeaEvent {

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

    public String getKirjauspäivä() { return kirjauspäivä; }
    public String getArvopäivä() { return arvopäivä; }
    public String getMaksupäivä() { return maksupäivä; }
    public String getMäärä() { return määrä; }
    public String getSaajaTaiMaksaja() { return saajaTaiMaksaja; }
    public String getTilinumero() { return tilinumero; }
    public String getBic() { return bic; }
    public String getTapahtuma() { return tapahtuma; }
    public String getViite() { return viite; }
    public String getMaksajanViite() { return maksajanViite; }
    public String getKortinnumero() { return kortinnumero; }
    public String getKuitti() { return kuitti; }


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
