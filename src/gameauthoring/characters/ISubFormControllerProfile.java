package gameauthoring.characters;

import engine.definitions.ProfileDefinition;


public interface ISubFormControllerProfile extends ISubFormController {
    
    void updateGameModel (ProfileDefinition item);

    void populateViewsWithData (ProfileDefinition item);
}
