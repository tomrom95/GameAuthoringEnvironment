package gameauthoring.creation.subforms;

import gameauthoring.creation.entryviews.FormDataManager;
import gameauthoring.creation.entryviews.IFormDataManager;


/**
 * Organizes and Displays a given grouping of EntryView Objects, according to specific needs of user
 * 
 * @author JoeLilien
 *
 */
public abstract class SubFormView implements ISubFormView {

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

    /**
     * Initializes arrangement and actual appearence of SFV
     */
    protected abstract void initView ();

}
