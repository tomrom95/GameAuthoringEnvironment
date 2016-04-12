package engine.profile;

import graphics.ImageGraphic;


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
    String getName ();

    /**
     * Gets the description
     *
     * @return A SimpleStringProperty containing the name of the sprite
     */
    String getDescription ();

    /**
     * Get's the sprite display graphic
     *
     * @return The ImageGraphic
     */
    ImageGraphic getImage ();

}
