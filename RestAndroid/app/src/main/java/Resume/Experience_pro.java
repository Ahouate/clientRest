package Resume;

import org.simpleframework.xml.Element;

/**
 * Created by Abdel on 02/04/2015.
 */
@Element(name= "experience_pro")
public class Experience_pro {
    @Element(name="date", required=false)
    public String date;
    @Element(name="experience", required=false)
    public String experience;

    public Experience_pro() {
    }

    public Experience_pro(String date, String experience) {
        this.date = date;

        this.experience = experience;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
