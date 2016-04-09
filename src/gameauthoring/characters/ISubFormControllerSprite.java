package gameauthoring.characters;

import engine.definitions.ProfileDefinition;
import engine.definitions.SpriteDefinition;


public interface ISubFormControllerSprite extends ISubFormController {
    
    void updateGameModel (SpriteDefinition item);

    void populateViewsWithData (SpriteDefinition item);
}
