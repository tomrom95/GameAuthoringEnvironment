package engine;

public interface ILevel extends Updateable {

    void addSprite (ISprite sprite);

    void addCondition (ICondition condition);

    void addGlobalAttribute (IAttribute attribute);

    void addGlobalResource (IResource resource);

}
