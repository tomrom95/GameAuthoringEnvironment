package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;


/**
 * This interface represents the basic info (profile) of a Sprite
 * 
 * @author Jeremy Schreck
 *
 */
public interface IProfile {

    /**
     * Gets the sprite's type property
     * 
     * @return A SimpleStringProperty containing the name of the sprite
     */
    ObjectProperty<SpriteType> getSpriteType ();

    /**
     * Gets the filepath property containing the sprite's image
     * 
     * @return The image filepath as a StringProperty
     */
    StringProperty getImageFilepathProperty ();
}
