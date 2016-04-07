package gameauthoring.characters;

import gameauthoring.Glyph;

/**
 * This interface specifies the methods each SubFormView must implement to allow
 * its SubFormController to control it
 * 
 * @author Jeremy Schreck
 *
 */
public interface ISubFormView extends Glyph {

    /**
     * Get the data entered in the SubFormView
     * 
     * @return An IFormDataManager object that holds the data
     */
    IFormDataManager getData ();

    /**
     * Populate the SubFormView's fields with new data
     * 
     * @param data An IFormDataManager object containing the new data
     */
    void populateWithData (IFormDataManager data);
}
