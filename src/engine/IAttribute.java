package engine;

public interface IAttribute {
    AttributeType getType ();

    void addEffect (IEffect myEffect);

    double getValue ();

    void setValue (double valueToSet);

}
