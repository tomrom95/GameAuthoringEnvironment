package gameauthoring.creation.subforms;

import engine.IGame;
import gameauthoring.creation.subforms.ISubFormControllerSprite;


public abstract class DynamicSFCFactory {

    private IGame myGame;

    public DynamicSFCFactory (IGame game) {

        myGame = game;
    }

    public abstract ISubFormControllerSprite createSpriteSubFormController (String type);

    protected IGame getMyGame () {
        return myGame;
    }
}
