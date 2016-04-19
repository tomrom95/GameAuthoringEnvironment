package gameauthoring.creation.subforms.movement;

import java.util.List;
import engine.IGame;
import java.util.ArrayList;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingMoverDefinition;
import engine.sprite.SpriteType;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

public class TrackingMoverSubFormController implements ISubFormControllerSprite {


    private TrackingMoverSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private double myDefaultSpeed = 0;
    
    public TrackingMoverSubFormController (IGame game) {
       this.myGame = game;
       this.myView = new TrackingMoverSubFormView();
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
        Double mySpeedDouble = Double.valueOf(myFormData.getValueProperty(myView.getSpeedKey()).get());
        newTrackingMoverDef.setSpeed(mySpeedDouble);
        String[] targetStrings = myFormData.getValueProperty(myView.getTargetsKey()).get().split(", ");
        List<SpriteType> targets = new ArrayList<SpriteType>();
        for(String s : targetStrings){
            targets.add(new SpriteType(s));
        }
        newTrackingMoverDef.setTargets(targets);
        
        item.setMovementDefinition(newTrackingMoverDef);
        
    }
//    @Override
//    public void populateViewsWithData (SpriteDefinition item) {
//        TrackingMoverDefinition thisTMD = (TrackingMoverDefinition) item.getMovementDefinition();
//        myFormData.set(myView.getSpeedKey(), Double.toString(item.getMovementDefinition().getSpeed()));
//        
//        StringBuilder targetListString = new StringBuilder();
//        List<SpriteType> targetList = thisTMD.getTargets();
//        for(SpriteType s : targetList){
//            targetListString.append(s);
//            if(targetList.indexOf(s) != targetList.size() - 1){
//                targetListString.append(", ");
//            }
//        }
//        String targetsString = targetListString.toString();
//        myFormData.set(myView.getTargetsKey(), targetsString);
//        
//    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

  

}
