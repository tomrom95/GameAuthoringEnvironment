package engine.profile;

import graphics.IGraphic;
import javafx.beans.property.DoubleProperty;
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
     * Gets the sprite display graphic
     *
     * @return The IGraphic
     */
    IGraphic getImage ();

    /**
     * Get the url of the ImageGraphic
     * 
     * @return A ImageGraphic's url as a string
     */
    String getImageURL ();

    /**
     * Gets the width of image
     * 
     * @return
     */
    DoubleProperty getImageWidth ();

    /**
     * Gets the height of image
     * 
     * @return
     */
    DoubleProperty getImageHeight ();
    
    void setNew (String name, String desc, String url, double width, double height);

}
