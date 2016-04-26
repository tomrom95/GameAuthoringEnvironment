package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;

import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.FirerDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import gameauthoring.creation.factories.DynamicSFCFactory;
import gameauthoring.creation.subforms.DynamicSubFormController;

/**
 * NOT USED WITH CURRENT IMPLEMENTATION
 */
/**
 * This is the sfc for selecting firing modules for a SpriteDefinition
 * 
 * @author Jeremy Schreck
 *
 */

public class FiringSFC extends DynamicSubFormController<SpriteDefinition> {

    public FiringSFC (IGame game,
                      DynamicSFCFactory<SpriteDefinition> sfcFactory,
                      List<String> subFormIDs) {
        super(game, sfcFactory, subFormIDs);
        // TODO Auto-generated constructor stub
    }

    private SpriteDefinition myMissile;

    /**
     * Constructor
     * 
     * @param game The current game object
     */
//    public FiringSFC (IGame game) {
//        super(game, new FiringSFCFactory(game, null),
//              new ArrayList<String>(Arrays.asList("DirectionalFire", "TrackingFire")));
//        List<String> options = new ArrayList<>(Arrays.asList("Directional", "Tracking"));
//        setMyView(
//                  new FiringSFV(getMySubFormViews(), e -> changeSelection(e), options,
//                                        e -> changeMissile(e),
//                                        game.getAuthorshipData().getMyCreatedMissiles()
//                                                .getItems()));
//    }
//
//    @Override
//    protected void setUpSubFormControllers (List<String> subFormIDs) {
//        setMySFCFactory(new FiringSFCFactory(getMyGame(), this));
//        super.setUpSubFormControllers(subFormIDs);
//
//    }

    /**
     * The method handler called when the user changes which missile is selected
     * 
     * @param missile
     */
    private void changeMissile (SpriteDefinition missile) {
        myMissile = missile;

    }

    public SpriteDefinition getMyMissile () {
        return myMissile;
    }


}
