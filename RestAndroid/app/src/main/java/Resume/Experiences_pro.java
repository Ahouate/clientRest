package Resume;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.LinkedList;

/**
 * Created by Abdel on 26/04/2015.
 */
@Element(name= "experiences_pro")
public class Experiences_pro {
    @ElementList(entry="experience_pro", inline=true, required=false)
    public LinkedList<Experience_pro> experience_pro;

    public Experiences_pro() {
        experience_pro = new LinkedList<Experience_pro>();
    }

    public Experiences_pro(LinkedList<Experience_pro> experiences_pro) {
        this.experience_pro = experiences_pro;
    }

    public LinkedList<Experience_pro> getExperience_pro() {
        return experience_pro;
    }
    public void setExperience_pro(LinkedList<Experience_pro> experiences_pro) {
        this.experience_pro = experiences_pro;
    }
}
