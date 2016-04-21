package gameauthoring.listdisplay;

import engine.IGame;


public class ConditionViewFactory {

    private IGame myGame;

    public ConditionViewFactory (IGame game) {
        myGame = game;
    }

    public ConditionPopUp get (String selection) {

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
        }

        return null;
    }
}
