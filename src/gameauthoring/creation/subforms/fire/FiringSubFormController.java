package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

/**
 * This is the sfc for selecting firing modules for a Sprite
 * 
 * @author Jeremy Schreck
 *
 */
public class FiringSubFormController implements ISubFormControllerSprite {

    private FiringSubFormView myView;
    private ObservableList<ISubFormView> mySubFormViews;
    private List<ISubFormController<SpriteDefinition>> mySubFormControllers;
    private ISubFormController<SpriteDefinition> myCurrentFiringController;
    private IGame myGame;
    private SpriteDefinition myMissile;

    public FiringSubFormController (IGame game) {
        myGame = game;
        setUpSubFormControllers();
        setUpSubFormViews(mySubFormControllers);
        this.myView = new FiringSubFormView(mySubFormViews, e -> changeFiringType(e), e -> changeMissile(e), game.getAuthorshipData().getMyCreatedMissiles().getItems());
    }

    private void setUpSubFormControllers () {
        // TOOD: add to factory
        // TODO: add game to constructor
        DirectionalFireSubFormController dfSFC = new DirectionalFireSubFormController(myGame);
        TrackingFireSubFormController tfSFC = new TrackingFireSubFormController(myGame);
        mySubFormControllers = new ArrayList<>();
        mySubFormControllers.addAll(Arrays
                .asList(dfSFC, tfSFC));
        
        myCurrentFiringController = mySubFormControllers.get(0);

    }

    private void setUpSubFormViews (List<ISubFormController<SpriteDefinition>> subFormControllers) {
        mySubFormViews = FXCollections.observableArrayList();
        for (ISubFormController<SpriteDefinition> sfc : subFormControllers) {
            mySubFormViews.add(sfc.getSubFormView());
        }

    }

    
    // firing type combo box handler
    private void changeFiringType (int comboSelectionIndex) {
        myCurrentFiringController = mySubFormControllers.get(comboSelectionIndex);
        myView.changeSubMovementView(comboSelectionIndex);

    }
    
    // missile combo box handler
    private void changeMissile (SpriteDefinition missile) {
        System.out.println(missile.getProfile().getName().get());
        myMissile = missile;

    }

    @Override
    public void updateItem (SpriteDefinition item) {
        myCurrentFiringController.updateItem(item);
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myCurrentFiringController.populateViewsWithData(item);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }
    
    protected SpriteDefinition getMyMissile() {
        return myMissile;
    }

}
