package gameauthoring.characters;

import engine.definitions.MovementDefinition;

public interface ISubFormControllerMovement extends ISubFormController{

    void updateGameModel (MovementDefinition item);

    void populateViewsWithData (MovementDefinition item);
}
