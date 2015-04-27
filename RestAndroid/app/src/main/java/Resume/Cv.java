package Resume;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.LinkedList;

/**
 * Created by Abdel on 02/04/2015.
 */
@Element(name="resume")
public class Cv {
    @Element(name="competances", required=false)
    public Competances competance;
    @Element(name="experiences_pro", required=false)
    public Experiences_pro experience_pro;
    @Element(name="formations", required=false)
    public Formations formation;
    @Element(name="info_perso", required=false)
    public Info_perso info_perso;
    @Element(name="langues", required=false)
    public Langues langue;
    @Element(name="projets", required=false)
    public Projets projet;
    @Element(name="loisirs", required=false)
    public Loisirs loisir;
    @Element(name="titre", required=false)
    public String titre;

    public Cv() {
    }

    public Cv(String titre, Info_perso info_persos, Formations formations, Experiences_pro experiences_pro, Projets projets, Competances competance, Langues langues, Loisirs loisirs) {
        this.titre = titre;
        this.info_perso = info_persos;
        this.formation = formations;
        this.experience_pro = experiences_pro;
        this.projet = projets;
        this.competance = competance;
        this.langue = langues;
        this.loisir = loisirs;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Info_perso getInfo_perso() {
        return info_perso;
    }
    public void setInfo_perso(Info_perso info_persos) {
        this.info_perso = info_persos;
    }

    public Formations getFormations() {
        return formation;
    }
    public void setFormations(Formations formations) {
        this.formation = formations;
    }

    public Experiences_pro getExperiences_pro() {
        return experience_pro;
    }
    public void setExperiences_pro(Experiences_pro experiences_pro) {
        this.experience_pro = experiences_pro;
    }

    public Projets getProjets() {
        return projet;
    }
    public void setProjets(Projets projets) {
        this.projet = projets;
    }

    public Competances getCompetances() {
        return competance;
    }
    public void setCompetances(Competances competances) {
        this.competance = competances;
    }

    public Langues getLangues() {
        return langue;
    }
    public void setLangues(Langues langues) {
        this.langue = langues;
    }

    public Loisirs getLoisirs() {
        return loisir;
    }
    public void setLoisirs(Loisirs loisirs) {
        this.loisir = loisirs;
    }
}
