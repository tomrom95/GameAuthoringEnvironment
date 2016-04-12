package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.List;
import gameauthoring.creation.entryviews.FormDataManager;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.util.Glyph;


/**
 * Organizes and Displays a given grouping of EntryView Objects, according to specific needs of user
 * 
 * @author JoeLilien
 *
 */
public abstract class SubFormView implements ISubFormView{

    private List<IEntryView> myEntryViews;
    private IFormDataManager myData = new FormDataManager();
    
   

    /**
     * Implementation of getData() with Manager, think I like this one better. The same instance of
     * manager will be passed to all entry views via constructor and getData() method in EntryView
     * will be implemented to update map with new data upon save
     * 
     * @return
     */
    @Override
    public IFormDataManager getData () {
        return myData;
    }
   
    public List<IEntryView> getMyEntryViews () {
        return myEntryViews;
    }

    public void setMyEntryViews (List<IEntryView> myEntryViews) {
        this.myEntryViews = myEntryViews;
    }

    
}
