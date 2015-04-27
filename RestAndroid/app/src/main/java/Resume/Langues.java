package Resume;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.LinkedList;

/**
 * Created by Abdel on 26/04/2015.
 */
@Element(name= "langues")
public class Langues {
    @ElementList(entry="langue", inline=true, required=false)
    public LinkedList<Langue> langue;

    public Langues() {
        langue = new LinkedList<Langue>();
    }

    public Langues(LinkedList<Langue> langues) {
        this.langue = langues;
    }

    public LinkedList<Langue> getLangue() {
        return langue;
    }

    public void setLangue(LinkedList<Langue> langues) {
        this.langue = langues;
    }
}
