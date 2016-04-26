package gameauthoring.creation.subforms.movement;

import java.util.List;
import engine.IGame;
import engine.SpriteGroup;
import java.util.ArrayList;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.TrackingMoverDefinition;
import engine.sprite.SpriteType;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class TrackingMoverSFC implements ISubFormControllerSprite {

    private TrackingMoverSFV myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private double myDefaultSpeed = 0;

    public TrackingMoverSFC (IGame game) {
        this.myGame = game;
        this.myView = new TrackingMoverSFV(game.getAuthorshipData().getMyCreatedGroups());
        this.myFormData = myView.getData();
    }

    @Override
    public void initializeFields () {
        myFormData.set(myView.getSpeedKey(), Double.toString(myDefaultSpeed));
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        TrackingMoverDefinition newTrackingMoverDef = new TrackingMoverDefinition();
        newTrackingMoverDef.setGame(myGame);
        Double mySpeedDouble =
                Double.valueOf(myFormData.getValueProperty(myView.getSpeedKey()).get());
        newTrackingMoverDef.setSpeed(mySpeedDouble);
        newTrackingMoverDef.setTargets(myView.getTargetsCoice().getSelected());
    

        item.setMovementDefinition(newTrackingMoverDef);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        TrackingMoverDefinition movDef = (TrackingMoverDefinition) item.getMovementDefinition();
        myFormData.set(myView.getSpeedKey(), Double.toString(item.getMovementDefinition().getSpeed()));
        
        myView.getTargetsCoice().setSelected(movDef.getTargets());
        
       
    }

}
