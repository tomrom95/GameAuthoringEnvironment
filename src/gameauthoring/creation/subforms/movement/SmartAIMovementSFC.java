package gameauthoring.creation.subforms.movement;

import engine.IGame;
import engine.aipathing.GoalBasedMover;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.AIPatherDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class SmartAIMovementSFC implements ISubFormControllerSprite {

    private SmartAIMovementSFV myView;
    private double myDefaultSpeed = 0;
    
    private IGame myGame;

    public SmartAIMovementSFC (IGame game) {
        myView = new SmartAIMovementSFV();
        myGame = game;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        AIPatherDefinition aiPather = new AIPatherDefinition();
        aiPather.setGame(myGame);
        aiPather.setSpeed(myView.getMySpeed());
        item.setMovementDefinition(aiPather);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void initializeFields () {
        myView.populateWithData(myDefaultSpeed);

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.populateWithData(item.getMovementDefinition().getSpeed());
    }

}
