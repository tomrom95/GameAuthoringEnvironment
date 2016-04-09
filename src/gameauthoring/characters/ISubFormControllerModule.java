package gameauthoring.characters;

import engine.definitions.ModuleDefinition;

public interface ISubFormControllerModule extends ISubFormController {

    void updateGameModel (ModuleDefinition item);

    void populateViewsWithData (ModuleDefinition item);
}
