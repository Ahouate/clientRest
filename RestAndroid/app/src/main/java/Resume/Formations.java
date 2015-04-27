package Resume;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.LinkedList;

/**
 * Created by Abdel on 26/04/2015.
 */

@Element(name = "formations")
public class Formations {
    @ElementList(entry="formation", inline=true, required=false)
    public LinkedList<Formation> formation;

    public Formations() {
        formation = new LinkedList<Formation>();
    }

    public Formations(LinkedList<Formation> formations) {
        this.formation = formations;
    }

    public LinkedList<Formation> getFormation() {
        return formation;
    }

    public void setFormation(LinkedList<Formation> formations) {
        this.formation = formations;
    }
}
