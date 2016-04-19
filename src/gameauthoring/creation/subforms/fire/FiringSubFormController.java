package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.DynamicSubFormController;


/**
 * This is the sfc for selecting firing modules for a Sprite
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSubFormController extends DynamicSubFormController {

    private SpriteDefinition myMissile;

    public FiringSubFormController (IGame game) {
        super(game, new FiringSFCFactory(game, null),
              new ArrayList<String>(Arrays.asList("DirectionalFire", "TrackingFire")));
        List<String> options = new ArrayList<>(Arrays.asList("Directional", "Tracking"));
        setMyView(
                  new FiringSubFormView(getMySubFormViews(), e -> changeSelection(e), options,
                                        e -> changeMissile(e),
                                        game.getAuthorshipData().getMyCreatedMissiles()
                                                .getItems()));
    }

    @Override
    protected void setUpSubFormControllers (List<String> subFormIDs) {
        setMySFCFactory(new FiringSFCFactory(getMyGame(), this));
        super.setUpSubFormControllers(subFormIDs);

    }

    // missile combo box handler
    private void changeMissile (SpriteDefinition missile) {
        myMissile = missile;

    }

    public SpriteDefinition getMyMissile () {

        return myMissile;
    }



}
