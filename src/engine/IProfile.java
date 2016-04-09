package engine;

import javafx.beans.property.StringProperty;


/**
 * This interface represents the basic info (profile) of an object
 * 
 * @author Jeremy Schreck
 *
 */
public interface IProfile {

    /**
     * Gets the object's name
     * *
     * 
     * @return A SimpleStringProperty containing the name of the object
     */
    StringProperty getNameProperty ();

    /**
     * Get's the object's description property
     * 
     * @return The description of the object as a StringProperty
     */
    StringProperty getDescriptionProperty ();

    /**
     * Gets the filepath property containing the object's image
     * 
     * @return The image filepath as a StringProperty
     */
    StringProperty getImageFilepathProperty ();
}
