package Resume;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.LinkedList;

/**
 * Created by Abdel on 26/04/2015.
 */
@Element(name = "projets")
public class Projets {
    @ElementList(entry="projet", inline=true, required=false)
    public LinkedList<Projet> projet;

    public Projets() {
        projet = new LinkedList<Projet>();
    }

    public Projets(LinkedList<Projet> projets) {
        this.projet = projets;
    }

    public LinkedList<Projet> getProjet() {
        return projet;
    }

    public void setProjet(LinkedList<Projet> projets) {
        this.projet = projets;
    }
}

