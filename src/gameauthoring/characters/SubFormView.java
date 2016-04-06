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
   // private FormDataManager myData;

    public List<FormData> getData () {
        List<FormData> myData = new ArrayList<FormData>();
        for (EntryView e : myEntryViews) {
            myData.add(e.getData());
        }
        return myData;
    }

    /**
     * Implementation of getData() with Manager, think I like this one better. The same instance of
     * manager will be passed to all entry views via constructor and getData() method in EntryView
     * will be implemented to update map with new data upon save
     * 
     * @return
     */
    // public FormDataManager getData(){
    // for (EntryView e : myEntryViews) {
    // e.getData();
    // }
    // return myData;
    // }

    public List<EntryView> getMyEntryViews () {
        return myEntryViews;
    }

    public void setMyEntryViews (List<EntryView> myEntryViews) {
        this.myEntryViews = myEntryViews;
    }

}
