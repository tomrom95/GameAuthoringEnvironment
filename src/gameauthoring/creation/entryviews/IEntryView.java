package gameauthoring.creation.entryviews;

import gameauthoring.util.Glyph;


/**
 * Interface for class to essentially serve as wrapper for two javaFX objects: label and some data
 * entry object. These should be bundled and organized by specific subFormView classes
 * 
 * @author JoeLilien
 *
 */
public interface IEntryView extends Glyph {

    FormData getData ();

    void populateWithData (FormData data);
    
    //maybe make this properties to be able to get and set?

}
