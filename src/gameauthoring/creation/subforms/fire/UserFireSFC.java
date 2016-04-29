package gameauthoring.creation.subforms.fire;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.DirectionalFirerDefinition;
import engine.definitions.moduledef.FirerDefinition;
import engine.definitions.moduledef.UserFirerDefinition;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.movement.UserMoverSFV;
/**
 * 
 * @author josephtimko1
 *
 */
public class UserFireSFC extends RemovableFireSFC {
    
    private IUserFireSFV myView;
    private IGame myGame;
    private UserFirerDefinition myFireDef;
    private double myDefaultAngle = 0;
    private double myDefaultWaitTime = 0;
    private double myDefaultRange = 0;
    private boolean myDefaultRanged = false;
    private double myDefaultAngleStep = 0;
    private String myDefaultIncrease = "I";
    private String myDefaultDecrease = "D";
    private String myDefaultFire = "F";


    public UserFireSFC (FiringSFC sfc, IGame game) {
        super(sfc);
        init(game, new UserFirerDefinition(game));

    }

  

    public UserFireSFC (IGame game,
                        FiringSFC myFiringSFC,
                        UserFirerDefinition userFirerDefinition) {
        super(myFiringSFC);
        init(game, userFirerDefinition);
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        setMySpriteDefinition(item);
        myFireDef.setGame(myGame);
        myFireDef.setAngle(myView.getMyAngle());
        myFireDef.setAngleStep(myView.getMyAngleStep());
        myFireDef.setRanged(myView.getMyIsRanged());
        myFireDef.setFireRange(myView.getMyRange());
        myFireDef.setProjectileDefinition(myView.getMissileSelection());
    }
    
    private void init (IGame game, UserFirerDefinition userFirerDefinition) {
       myGame = game;
       myFireDef = userFirerDefinition;
       myView = new UserFireSFV(game.getAuthorshipData(), getRemoveMenu());
        
    }

    @Override
    public void initializeFields (SpriteDefinition item) {

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FirerDefinition getModuleDefinition () {
        return myFireDef;
    }

}
