package gameauthoring;

import javafx.beans.property.SimpleStringProperty;

/**
 * This interface represents the basic info (profile) of a Sprite
 * 
 * @author Jeremy Schreck
 *
 */
public interface IProfile {

    /**
     * Gets the sprite's name property
     * 
     * @return A SimpleStringProperty containing the name of the sprite
     */
    SimpleStringProperty getNameProperty();
    
    /**
     * Gets the filepath property containing the sprite's image
     * 
     * @return The image filepath as a SimpleStringProperty
     */
    SimpleStringProperty getImageFilepathProperty();
    
}
