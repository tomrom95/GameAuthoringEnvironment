package gameauthoring.characters;

import engine.definitions.ConditionDefinition;

public interface ISubFormControllerCondition extends ISubFormController {
    void updateGameModel (ConditionDefinition item);

    void populateViewsWithData (ConditionDefinition item);
}
