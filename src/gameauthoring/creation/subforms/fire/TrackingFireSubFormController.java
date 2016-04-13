package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.List;
import engine.IGame;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.definitions.TrackingMoverDefinition;
import engine.sprite.SpriteType;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * tracking movement modules and projectiles
 * 
 * @author Dhrumil
 *
 */

public class TrackingFireSubFormController implements ISubFormControllerSprite {

    private TrackingFireSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;

    public TrackingFireSubFormController (IGame game) {
        myView = new TrackingFireSubFormView();
        myFormData = myView.getData();
        myGame = game;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        TrackingFirerDefinition trackingFireDef = new TrackingFirerDefinition();
        trackingFireDef.setGame(myGame);
        Double waitTime = Double.valueOf(myFormData.getValueProperty(myView.getWaitTimeKey()).get());
        trackingFireDef.setWaitTime(waitTime);
        String[] targetStrings = myFormData.getValueProperty(myView.getTargetsKey()).get().split(", ");
        String projectile = myFormData.getValueProperty(myView.getMyProjectileKey()).get();
        List<SpriteType> targets = new ArrayList<SpriteType>();
        for(String s : targetStrings){
            targets.add(new SpriteType(s));
        }
        trackingFireDef.setTargets(targets);
        //trackingFireDef.setProjectileDefinition(new SpriteDefinition(projectile));
        item.addModule(trackingFireDef);
        
    }
    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        //TrackingFirerDefinition fireDefinition = (TrackingFirerDefinition)item;
        //myFormData.set(myView.getWaitTime(), Double.toString(item.getFireDefinition().getWaitTime));
        
        StringBuilder targetListString = new StringBuilder();
//        List<SpriteType> targetList = fireDefinition.getTargets();
//        for(SpriteType s : targetList){
//            targetListString.append(s);
//            if(targetList.indexOf(s) != targetList.size() - 1){
//                targetListString.append(", ");
//            }
//        }
        String targetsString = targetListString.toString();
        myFormData.set(myView.getTargetsKey(), targetsString);
        //myFormData.set(myView.getMyProjectileKey());
        
    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return myView;
    }

}
