package gameauthoring.creation.subforms.movement;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.TrackingMoverDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class TrackingMoverSFC implements ISubFormControllerSprite {

    private TrackingMoverSFV myView;
    private IGame myGame;
    private double myDefaultSpeed = 0;

    public TrackingMoverSFC (IGame game) {
        this.myGame = game;
        this.myView = new TrackingMoverSFV(game.getAuthorshipData().getMyCreatedGroups());
    }

    @Override
    public void initializeFields (SpriteDefinition item) {
        myView.populateWithData(myDefaultSpeed, null);
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        TrackingMoverDefinition newTrackingMoverDef = new TrackingMoverDefinition();
        newTrackingMoverDef.setGame(myGame);
        double mySpeedDouble = myView.getSpeed();
        newTrackingMoverDef.setSpeed(mySpeedDouble);
        newTrackingMoverDef.setTargets(myView.getTargetsCoice());
        item.setMovementDefinition(newTrackingMoverDef);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        TrackingMoverDefinition movDef = (TrackingMoverDefinition) item.getMovementDefinition();
        myView.populateWithData(movDef.getSpeed(), movDef.getTargets());
    }

}
