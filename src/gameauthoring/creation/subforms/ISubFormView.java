package gameauthoring.creation.subforms;

import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.util.Glyph;

/**
 * This interface specifies the methods each SubFormView must implement to allow
 * its SubFormController to control it
 * 
 * @author Jeremy Schreck
 * @author Joe Lilien
 *
 */
public interface ISubFormView extends Glyph {

    /**
     * Get the data entered in the SubFormView
     * 
     * @return An IFormDataManager object that holds the data
     */
    IFormDataManager getData ();
    
    

}
