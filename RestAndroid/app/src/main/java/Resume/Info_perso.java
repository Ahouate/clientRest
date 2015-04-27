package Resume;

import org.simpleframework.xml.Element;

/**
 * Created by Abdel on 02/04/2015.
 */
@Element(name= "info_perso")
public class Info_perso {
    @Element(name="nom", required=false)
    public String nom;
    @Element(name="prenom", required=false)
    public String prenom;
    @Element(name="adresse", required=false)
    public String adresse;
    @Element(name="tele", required=false)
    public String tele;

    public Info_perso(String nom, String prenom, String adresse, String tele) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tele = tele;
    }

    public Info_perso() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }
}
