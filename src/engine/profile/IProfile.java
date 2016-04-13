package engine.profile;

import graphics.IGraphic;
import javafx.beans.property.SimpleObjectProperty;
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
    StringProperty getName ();

    /**
     * Gets the description
     *
     * @return A SimpleStringProperty containing the name of the sprite
     */
    StringProperty getDescription ();

    /**
     * Get's the sprite display graphic
     *
     * @return The IGraphic
     */
    SimpleObjectProperty<? extends IGraphic> getImage ();

    /**
     * Get the url of the ImageGraphic
     * 
     * @return A ImageGraphic's url as a string
     */
    String getImageURL ();
    
    void setNew (String name, String desc, String url);
    
}
