package gameauthoring.listdisplay;

import engine.IGame;
import engine.ILevel;


public class ConditionViewFactory {

    private IGame myGame;
    private ILevel myLevel;

    public ConditionViewFactory (IGame game) {
        myGame = game;
    }
    
    public ConditionViewFactory (IGame game, ILevel level) {
        this(game);
        myLevel = level;
    }

    public SubConditionView get (String selection) {

        // TODO replace with reflection
        switch (selection) {
            case "OnClickCondition":
                return new OnClickView(myGame);
            case "OnCollisionCondition":
                return new OnCollisionView(myGame);
            case "OnGlobalAttribute":
                return new OnGlobalView(myGame);
            case "OnSpriteAttribute":
                return new OnSpriteView(myGame);
            case "Global Attribute End Condition":
                return new GlobalEndView(myGame, myLevel);
            case "Sprite Attribute End Condition":
                return new SpriteEndView(myGame, myLevel);
        }

        return null;
    }
}
