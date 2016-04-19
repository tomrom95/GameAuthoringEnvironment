package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import engine.IGame;
import engine.definitions.FirerDefinition;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.DynamicSubFormController;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.SubFormControllerFactory;


/**
 * This is the sfc for selecting firing modules for a Sprite
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSubFormController extends DynamicSubFormController {

    private SpriteDefinition myMissile;

    public FiringSubFormController (SubFormControllerFactory sfcFactory, IGame game) {
        super(sfcFactory, game);
        List<String> options = new ArrayList<>(Arrays.asList("Directional", "Tracking"));
        setMyView(
                  new FiringSubFormView(getMySubFormViews(), e -> changeSelection(e), options,
                                        e -> changeMissile(e),
                                        game.getAuthorshipData().getMyCreatedMissiles()
                                                .getItems()));
    }

    protected void setUpSubFormControllers () {
        // TOOD: add to factory
        // gonna have to figure out better way to get access to getMyMissile
        DirectionalFireSubFormController dfSFC =
                (DirectionalFireSubFormController) this.getMySFCFactory()
                        .createSpriteSubFormController("DirectionalFire");
        TrackingFireSubFormController tfSFC =
                (TrackingFireSubFormController) this.getMySFCFactory()
                        .createSpriteSubFormController("TrackingFire");
        List<ISubFormController<SpriteDefinition>> subFormControllers = new ArrayList<>();
        subFormControllers.addAll(Arrays
                .asList(dfSFC, tfSFC));
        setMySubFormControllers(subFormControllers);

    }

    // missile combo box handler
    private void changeMissile (SpriteDefinition missile) {
        myMissile = missile;

    }

    public SpriteDefinition getMyMissile () {

        return myMissile;
    }

    @Override
    protected void setMyCurrentSFC () {
        setMyCurrentSubFormController(getMySubFormControllers().get(0));
    }

}
