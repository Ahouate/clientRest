package Resume;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.LinkedList;

/**
 * Created by Abdel on 16/04/2015.
 */
@Root(strict=false)
public class ListeCv {

    @ElementList(entry="resume", inline=true, required=false)
    public LinkedList<Cv> resume;

    public ListeCv(){
        resume= new LinkedList<Cv>();
    }

    public LinkedList<Cv> getResume() {
        return resume;
    }

    public void setResume(LinkedList<Cv> resume) {
        resume = resume;
    }

    public void add(Cv cv){
        resume.add(cv);
    }

    public Cv find(int i){
        if(i<=resume.size())
            return resume.get(i-1);
        return null;
    }

    public void delete(int i){
        if(i<=resume.size())
             resume.remove(i-1);
    }
}
