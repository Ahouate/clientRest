package Resume;

import org.simpleframework.xml.Element;

/**
 * Created by Abdel on 02/04/2015.
 */
@Element(name= "langue")
public class Langue {
    @Element(name="nom", required=false)
    public String nom;
    @Element(name="niveau", required=false)
    public String niveau;

    public Langue() {
    }

    public Langue(String nom, String niveau) {

        this.nom = nom;
        this.niveau = niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
