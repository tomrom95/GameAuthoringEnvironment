package engine;

public interface ILevel {

    void addSprite (ISprite sprite);

    void addCondition (ICondition condition);

    void addGlobalAttribute (IAttribute attribute);

    void addGlobalResource (IResource resource);

    void update ();

}
