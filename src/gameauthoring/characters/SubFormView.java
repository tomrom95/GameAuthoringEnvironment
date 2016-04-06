package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;
import gameauthoring.Glyph;


/**
 * Organizes and Displays a given grouping of EntryView Objects, according to specific needs of user
 * 
 * @author JoeLilien
 *
 */
public abstract class SubFormView implements Glyph {

    private List<EntryView> myEntryViews;

    public List<FormData> getData () {
        List<FormData> myData = new ArrayList<FormData>();
        for (EntryView e : myEntryViews) {
            myData.add(e.getData());
        }
        return myData;
    }

    public List<EntryView> getMyEntryViews () {
        return myEntryViews;
    }

    public void setMyEntryViews (List<EntryView> myEntryViews) {
        this.myEntryViews = myEntryViews;
    }

}
