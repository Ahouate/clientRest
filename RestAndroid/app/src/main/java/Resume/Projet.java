package Resume;

import org.simpleframework.xml.Element;

/**
 * Created by Abdel on 02/04/2015.
 */
@Element(name = "projet")
public class Projet {
    @Element(name="date", required=false)
    public String date;
    @Element(name="sujet", required=false)
    public String sujet;

    public Projet() {
    }

    public Projet(String date, String sujet) {
        this.date = date;
        this.sujet = sujet;

    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }
}
