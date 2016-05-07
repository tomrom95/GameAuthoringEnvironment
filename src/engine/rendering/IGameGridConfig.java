package engine.rendering;

/**
 * This class will encapsulate the desired configuration of the internal virtual
 * pixel grid of the game, and thus the necessary information to support scaling
 * properly
 *
 * The only part that is necessary now is the {@link #getGridHeight()} and the
 * {@link #getGridWidth()}
 * functionality as that will allow for proper screen edge checking and will assist with some
 * internal
 * classes that need a notion of the bounded internal game grid
 *
 * @author jonathanim
 *
 */
public interface IGameGridConfig {
    /**
     * @return The width of the virtual pixel grid to be considered the
     *         entirety of actual game space
     */
    int getGridWidth ();

    /**
     * The height of the virtual pixel grid to be considered the entirety
     * of the actual game space
     *
     * @return
     */
    int getGridHeight ();

    /**
     * Will need to use this method in for example the event handlers in order to
     * translate clicks to virtual coordinates, also the renderer should
     * eventually use this to support draggable screens
     *
     * @return The value to multiply xcoordinate virtual values by in order to
     *         recover the real display pixel values
     */

    double getXScalingFactor ();

    /**
     * Will need to use this method in for example the event handlers in order to
     * translate clicks to virtual coordinates, also the renderer should
     * eventually use this to support draggable screens
     *
     * @return The value to multiply ycoordinate virtual values by in order to
     *         recover the real display pixel values
     */
    double getYScalingFactor ();

    /**
     * This should only be used in the event that we implement scaling by the listeners that handle
     * the widow drag event to calculate the new ratio of real pixels to virtual
     *
     * @param scaleX The new x virtual -> real scaling factor
     */
    void setXScalingFactor (double scaleX);

    /**
     * This should only be used in the event that we implement scaling by the listeners that handle
     * the widow drag event to calculate the new ratio of real pixels to virtual
     *
     * @param scaleY The new y virtual -> real scaling factor
     */
    void setYScalingFactor (double scaleY);
}
