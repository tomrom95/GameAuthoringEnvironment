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

public class DirectionalFireSFC extends RemovableSpriteSFC {

    private IDirectionalFireSFV myView;
    private IGame myGame;
    private double myDefaultAngle = 0;
    private double myDefaultWaitTime = 0;
    private DirectionalFirerDefinition myFireDef;

    public DirectionalFireSFC (IGame game, FiringSFCmult sfc, DirectionalFirerDefinition fireDef) {
        super(sfc);
        myFireDef = fireDef;
        myView =
                new DirectionalFireSFV(game.getAuthorshipData().getMyCreatedMissiles(),
                                       getRemoveMenu());
        myGame = game;
    }

    @Override
    public void initializeFields () {
        populateViewsWithData(myDefaultAngle, myDefaultWaitTime);
    }

    private void populateViewsWithData (double angle, double wait) {
        myView.populateWithData(null, myDefaultAngle, myDefaultWaitTime);
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
            myFireDef.setGame(myGame);
            myFireDef.setAngle(angle);
            myFireDef.setWaitTime(waitTime);
            myFireDef.setProjectileDefinition(myView.getMissileSelection());
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
    public FirerDefinition getFirerDefinition () {
        return myFireDef;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.populateWithData(myFireDef.getProjectileDefinition(),myFireDef.getAngle(),myFireDef.getWaitTime());

    }

}
