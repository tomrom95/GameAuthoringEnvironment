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
public abstract class SubFormView implements ISubFormView{

    private List<EntryView> myEntryViews;
    private IFormDataManager myData;

    public SubFormView() {
        
    }
    
    public SubFormView(IFormDataManager formDataManager){
        myData = formDataManager;
    }
    
   

    /**
     * Implementation of getData() with Manager, think I like this one better. The same instance of
     * manager will be passed to all entry views via constructor and getData() method in EntryView
     * will be implemented to update map with new data upon save
     * 
     * @return
     */
    @Override
    public IFormDataManager getData () {
        for (EntryView e : getMyEntryViews()) {
            myData.add(e.getData());
        }
        return myData;
    }
   

    @Override
    public void populateWithData(IFormDataManager data) {
        
    }
    
    
    
    public List<EntryView> getMyEntryViews () {
        return myEntryViews;
    }

    public void setMyEntryViews (List<EntryView> myEntryViews) {
        this.myEntryViews = myEntryViews;
    }

    
}
