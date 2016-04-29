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
    public void initializeFields (SpriteDefinition item) {
        populateViewsWithData(myDefaultAngle, myDefaultWaitTime, myDefaultRange);
    }

    private void populateViewsWithData (double angle, double wait, double range) {
        myView.populateWithData(null, myDefaultAngle, myDefaultWaitTime);
        //TODO: deal with range stuff
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
            //TODO: include range
            //double range = myView.ge
            myFireDef.setGame(myGame);
            myFireDef.setAngle(angle);
            myFireDef.setWaitTime(waitTime);
            myFireDef.setProjectileDefinition(myView.getMissileSelection());


//            myFireDef.setRanged(myView.isRangedProperty().get());
//            myFireDef.setFireRange(range);

            if (!item.getModuleDefinitions().contains(myFireDef)) {
                item.addModule(myFireDef);
            }
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

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        //TODO: range
        myView.populateWithData(myFireDef.getProjectileDefinition(),myFireDef.getAngle(),myFireDef.getWaitTime());

    }

}
