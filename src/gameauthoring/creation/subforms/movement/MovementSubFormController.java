package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.DynamicSubFormController;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.SubFormControllerFactory;


public class MovementSubFormController extends DynamicSubFormController {

    private StaticMoverSubFormController myStaticSubForm;
    private ConstantMoverSubFormController myConstantSubForm;
    private UserMoverSubFormController myUserSubForm;
    private TrackingMoverSubFormController myTrackingSubForm;

    public MovementSubFormController (SubFormControllerFactory sfcFactory, IGame game) {
        super(sfcFactory, game);
        List<String> options =
                new ArrayList<String>(Arrays.asList("Static", "Constant", "User-Defined",
                                                    "Tracking"));
        setMyView(new MovementSubFormView(getMySubFormViews(), e -> changeSelection(e), options));
    }

    /*
     * private List<String> getOptions(){
     * List<String> options = new ArrayList<>();
     * for(ISubFormController sfc: getMySubFormControllers){
     * options.add(sfc.getTitle());
     * }
     * return options;
     * }
     */

    protected void setUpSubFormControllers () {
        myStaticSubForm = new StaticMoverSubFormController();
        myConstantSubForm = new ConstantMoverSubFormController();
        myUserSubForm = new UserMoverSubFormController();
        myTrackingSubForm = new TrackingMoverSubFormController(null); // TODO: add game to
                                                                      // constructor
        List<ISubFormController<SpriteDefinition>> subFormControllers = new ArrayList<>();
        subFormControllers.addAll(Arrays
                .asList(myStaticSubForm, myConstantSubForm, myUserSubForm, myTrackingSubForm));
        setMySubFormControllers(subFormControllers);

    }

    @Override
    protected void setMyCurrentSFC () {
        setMyCurrentSubFormController(getMySubFormControllers().get(0));

    }

}
