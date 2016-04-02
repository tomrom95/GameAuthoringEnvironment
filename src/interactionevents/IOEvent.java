package interactionevents;

public class IOEvent implements IInteractionEvent{

    private InputType myType;
    
    public IOEvent (InputType type) {
        myType = type;
    }
    
    @Override
    public InputType getType () {
        return myType;
    }

}
