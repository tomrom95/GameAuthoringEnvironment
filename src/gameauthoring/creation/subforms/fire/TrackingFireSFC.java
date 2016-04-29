package gameauthoring.creation.subforms.fire;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.FirerDefinition;
import engine.definitions.moduledef.TrackingFirerDefinition;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * tracking movement modules and projectiles
 * 
 * @author Dhrumil Timko Schreck Lilien
 *
 */

public class TrackingFireSFC extends RemovableSpriteSFC {

    private ITrackingFireSFV myView;
    private IGame myGame;
    private double myDefaultWaitTime = 0;
    private TrackingFirerDefinition myFireDef;


    public TrackingFireSFC (IGame game, FiringSFC sfc, TrackingFirerDefinition firingDef) {
        super(sfc);
        myFireDef = firingDef;
        myView = new TrackingFireSFV(game.getAuthorshipData(), getRemoveMenu());
        myGame = game;
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultWaitTime);
    }

    private void populateViewsWithData (double wait) {
        //TODO: maybe complete this
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        setMySpriteDefinition(item);
        myFireDef.setGame(myGame);
        double waitTime = myView.getWaitTime();
        myFireDef.setWaitTime(waitTime);
        myFireDef.setTargets(myView.getTargetsCoice());
        myFireDef.setProjectileDefinition(myView.getSelectedMissile());
        if (!item.getModuleDefinitions().contains(myFireDef)) {         
           item.addModule(myFireDef);
        }        
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public FirerDefinition getFirerDefinition () {
        return myFireDef;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.populateWithData(myFireDef.getProjectileDefinition(), myFireDef.getTargets(), myFireDef.getWaitTime());
    }

}
