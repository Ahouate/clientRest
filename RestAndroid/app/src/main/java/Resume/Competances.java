package Resume;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.LinkedList;

/**
 * Created by Abdel on 26/04/2015.
 */
@Element(name= "competances")
public class Competances {
    @ElementList(entry="competance", inline=true, required=false)
    public LinkedList<Competance> competance;

    public Competances() {
        competance = new LinkedList<Competance>();
    }

    public Competances(LinkedList<Competance> competances) {
        this.competance = competances;
    }

    public LinkedList<Competance> getCompetance() {
        return competance;
    }

    public void setCompetance(LinkedList<Competance> competances) {
        this.competance = competances;
    }
}
