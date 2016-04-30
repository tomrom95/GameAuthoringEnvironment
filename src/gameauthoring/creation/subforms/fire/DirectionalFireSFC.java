package gameauthoring.creation.subforms.fire;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.DirectionalFirerDefinition;
import engine.definitions.moduledef.FirerDefinition;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.ErrorMessage;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * linear, directional movement modules
 * 
 * @author Dhrumil Timko Schreck Lilien
 *
 */

public class DirectionalFireSFC extends RemovableFireSFC {

    private IDirectionalFireSFV myView;
    private IGame myGame;
    private double myDefaultAngle = 0;
    private double myDefaultWaitTime = 0;
    private DirectionalFirerDefinition myFireDef;
    private double myDefaultRange = 0;
    private boolean myDefaultRanged = false;

    public DirectionalFireSFC (IGame game, FiringSFC sfc) {
        super(sfc);
        init(game, new DirectionalFirerDefinition(game));
    }
    
    public DirectionalFireSFC (IGame game, FiringSFC sfc, DirectionalFirerDefinition fireDef) {
        super(sfc);
        init(game, fireDef);
      
    }

    private void init(IGame game, DirectionalFirerDefinition fireDef){
        myGame = game;
        myFireDef = fireDef;
        myView =
                new DirectionalFireSFV(game.getAuthorshipData().getMyCreatedMissiles(),
                                       getRemoveMenu());
    }
    
    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultAngle, myDefaultWaitTime, myDefaultRange, myDefaultRanged);
    }

    //TODO: What is this method, why does it pass in variables, seems redundant/not make sense - Timko
    private void populateViewsWithData (double angle, double wait, double range, boolean isRanged) {
        myView.populateWithData(null, myDefaultAngle, myDefaultWaitTime, myDefaultRange, myDefaultRanged);
}

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        setMySpriteDefinition(item);
        try {
            double angle = Math.toRadians(myView.getMyAngle()); // tangent functions need radians
            double waitTime = myView.getMyWaitTime();
            double range = myView.getMyRange();
            boolean isRanged = myView.getMyIsRanged();
            myFireDef.setGame(myGame);
            myFireDef.setAngle(angle);
            myFireDef.setWaitTime(waitTime);
            myFireDef.setProjectileDefinition(myView.getMissileSelection());
            myFireDef.setRanged(isRanged);
            myFireDef.setFireRange(range);

            item.addModule(myFireDef);
        }
        catch (Exception e) {
            ErrorMessage err =
                    new ErrorMessage("All Fields for Directional Firer Must Be Complete");
            err.showError();
        }
    }

    @Override
    public FirerDefinition getModuleDefinition () {
        return myFireDef;
    }

    //TODO why is this item taken in if we just use my firer def
    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.populateWithData(myFireDef.getProjectileDefinition(),myFireDef.getAngle() * 180 / Math.PI,myFireDef.getWaitTime(), myFireDef.getFireRange(), myFireDef.getRanged());

    }

}
