package gameauthoring.conditiontab;

import engine.IGame;


public class ConditionPopUpFactory {

    private IGame myGame;

    public ConditionPopUpFactory (IGame game) {
        myGame = game;
    }

    public ConditionPopUp get (String selection) {

        // TODO replace with reflection
        switch (selection) {
            case "OnClickCondition":
                return new OnClickPopUp(myGame);
            case "OnCollisionCondition":
                return new OnCollisionPopUp(myGame);
            case "OnGlobalAttribute":
                return new OnGlobalPopUp(myGame);
            case "OnSpriteAttribute":
                return new OnSpritePopUp(myGame);
        }

        return null;
    }
}
