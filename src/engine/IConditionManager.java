package engine;

public interface IConditionManager extends Updateable{

    void addCondition (ICondition condition);
    
    void update ();
}
