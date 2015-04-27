package Resume;

import org.simpleframework.xml.Element;

/**
 * Created by Abdel on 02/04/2015.
 */
@Element(name = "formation")
public class Formation {
    @Element(name="date", required=false)
    public String date;
    @Element(name="diplome", required=false)
    public String diplome;

    public Formation() {
    }

    public Formation(String date, String diplome) {
        this.date = date;
        this.diplome = diplome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }
}
