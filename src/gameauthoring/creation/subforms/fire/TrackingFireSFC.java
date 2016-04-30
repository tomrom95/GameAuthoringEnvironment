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

public class TrackingFireSFC extends RemovableFireSFC {

    private ITrackingFireSFV myView;
    private IGame myGame;
    private double myDefaultWaitTime = 0;
    private double myDefaultRange = 0;
    private boolean myDefaultIsRanged = false;
    private TrackingFirerDefinition myFireDef;

    public TrackingFireSFC (IGame game, FiringSFC sfc) {
        super(sfc);
        init(game, new TrackingFirerDefinition(game));
    }

    public TrackingFireSFC (IGame game, FiringSFC sfc, TrackingFirerDefinition firingDef) {
        super(sfc);
        init(game, firingDef);
    }

    private void init (IGame game, TrackingFirerDefinition fireDef) {
        myFireDef = fireDef;
        myView = new TrackingFireSFV(game.getAuthorshipData(), getRemoveMenu());
        myGame = game;
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultWaitTime, myDefaultRange, myDefaultIsRanged);
    }

    private void populateViewsWithData (double wait, double range, boolean isRanged) {
        myView.populateWithData(null, null, myDefaultWaitTime, myDefaultRange, myDefaultIsRanged);

    }

    @Override
    public void updateItem (SpriteDefinition item) {
        setMySpriteDefinition(item);
        myFireDef.setGame(myGame);
        double waitTime = myView.getMyWaitTime();
        myFireDef.setWaitTime(waitTime);
        myFireDef.setTargets(myView.getTargetsCoice());
        myFireDef.setProjectileDefinition(myView.getSelectedMissile());
        myFireDef.setRanged(myView.getMyIsRanged());
        myFireDef.setFireRange(myView.getMyRange());        
        item.addModule(myFireDef);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public FirerDefinition getModuleDefinition () {
        return myFireDef;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.populateWithData(myFireDef.getProjectileDefinition(), myFireDef.getTargets(),
                                myFireDef.getWaitTime(), myFireDef.getFireRange(), myFireDef.getRanged());
    }

}
