package gameauthoring.characters;

import engine.definitions.AttributeDefinition;

public interface ISubFormControllerAttribute extends ISubFormController {
    void updateGameModel (AttributeDefinition item);

    void populateViewsWithData (AttributeDefinition item);
}
