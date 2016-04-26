package engine.definitions.moduledef;

import engine.IGame;
import engine.Positionable;
import engine.aipathing.GoalBasedMover;
import engine.modules.IMovementModule;


public class AIPatherDefinition extends MovementDefinition {

    private IGame myGame;

    @Override
    public IMovementModule create (Positionable parent) {
        IMovementModule toReturn = new GoalBasedMover(parent, getGame(), false);
        toReturn.setSpeed(getSpeed());
        return toReturn;
    }

    public void setGame (IGame game) {
        myGame = game;
    }

    public IGame getGame () {
        return myGame;
    }

}
