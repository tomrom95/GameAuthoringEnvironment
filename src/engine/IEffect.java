package engine;

public interface IEffect {

    void applyToAttribute (IAttribute attribute);

    boolean hasCompleted ();
}
