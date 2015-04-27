package Resume;

import org.simpleframework.xml.Element;


/**
 * Created by Abdel on 02/04/2015.
 */
@Element(name= "competance")
public class Competance {
    @Element(name="titre", required=false)
    public String titre;
    @Element(name="description", required=false)
    public String description;

    public Competance() {
    }

    public Competance(String titre, String description) {
        this.titre = titre;
        this.description = description;

    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
