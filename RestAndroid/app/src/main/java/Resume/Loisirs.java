package Resume;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.LinkedList;

/**
 * Created by Abdel on 26/04/2015.
 */
@Element(name= "loisirs")
public class Loisirs {
    @ElementList(entry="loisir", inline=true, required=false)
    public LinkedList<String> loisir;

    public Loisirs() {
        this.loisir = new LinkedList<String>();
    }

    public Loisirs(LinkedList<String> loisirs) {
        this.loisir = loisirs;
    }

    public LinkedList<String> getLoisir() {
        return loisir;
    }

    public void setLoisir(LinkedList<String> loisirs) {
        this.loisir = loisirs;
    }
}
