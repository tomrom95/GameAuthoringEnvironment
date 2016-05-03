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
    private double myDefaultAngle;
    private double myDefaultWaitTime;
    private DirectionalFirerDefinition myFireDef;
    private double myDefaultRange;
    private boolean myDefaultRanged;

    public DirectionalFireSFC (IGame game, FiringSFC sfc) {
        super(sfc);
        init(game, new DirectionalFirerDefinition(game));
        initializeFields();
    }

    public DirectionalFireSFC (IGame game, FiringSFC sfc, DirectionalFirerDefinition fireDef) {
        super(sfc);
        init(game, fireDef);
        populateViewsWithData(null);

    }

    private void init (IGame game, DirectionalFirerDefinition fireDef) {
        // myNumbers = ResourceBundle
        // .getBundle("defaults/numbers");
        // myParser = new StringParser();
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

    private void populateViewsWithData (double angle, double wait, double range, boolean isRanged) {
        myView.populateWithData(null, myDefaultAngle, myDefaultWaitTime, myDefaultRange,
                                myDefaultRanged);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        setMySpriteDefinition(item);
        try {
            double angle = Math.toRadians(myView.getMyAngle());
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
        catch (NullPointerException e) {
            ErrorMessage err =
                    new ErrorMessage(getMyLabels().getString("IncompleteDirectionalFirer"));
            err.showError();
        }
    }

    @Override
    public FirerDefinition getModuleDefinition () {
        return myFireDef;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        double toDegrees = getParser().parseDouble(getMyNumbers().getString("ToDegrees"));
        myView.populateWithData(myFireDef.getProjectileDefinition(),
                                myFireDef.getAngle() * toDegrees,
                                myFireDef.getWaitTime(),
                                myFireDef.getFireRange(), myFireDef.getRanged());

    }

}
