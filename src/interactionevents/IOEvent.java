package interactionevents;

/**
 * This class serves as the converted event type that is understood by the GameEngine. This class
 * implements IInteractionEvent and creates an event that removes the JavaFX dependency.
 * 
 *
 */

public class IOEvent implements IInteractionEvent {

    private InputType myType;

    public IOEvent (InputType type) {
        myType = type;
    }

    @Override
    public InputType getType () {
        return myType;
    }

}
