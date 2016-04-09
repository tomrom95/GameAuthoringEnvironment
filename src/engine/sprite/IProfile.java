package engine.sprite;

import graphics.ImageGraphic;
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
     * Gets the sprite's name
     * 
     * @return A SimpleStringProperty containing the name of the sprite
     */
    StringProperty getNameProperty ();
    
    /**
     * Gets the description 
     * 
     * @return A SimpleStringProperty containing the name of the sprite
     */
    StringProperty getDescription ();


    /**
     * Get's the sprite display graphic
     * 
     * @return The ImageGraphic
     */
    ObjectProperty<ImageGraphic> getImage ();


}
