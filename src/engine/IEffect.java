package engine;

public interface IEffect {

    AttributeType getAttributeType ();
    
    void applyToAttribute (IAttribute attribute);

    boolean hasCompleted ();
}
