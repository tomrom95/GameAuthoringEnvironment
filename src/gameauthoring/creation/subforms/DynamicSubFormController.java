package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.List;
import engine.IGame;
import engine.definitions.SpriteDefinition;


/**
 * This in an abstract class for an SFC that needs to dynamically change its
 * subview based on user input
 * 
 * @author Jeremy Schreck
 *
 */
public abstract class DynamicSubFormController implements ISubFormControllerSprite {
    private DynamicSubFormView myView;
    private List<ISubFormView> mySubFormViews;
    private List<ISubFormController<SpriteDefinition>> mySubFormControllers;
    private ISubFormController<SpriteDefinition> myCurrentSubFormController;
    private IGame myGame;
    private DynamicSFCFactory mySFCFactory;

    /**
     * Constructor
     * 
     * @param game The current game object
     * @param sfcFactory A factory class for creating new sub-subforms
     * @param subFormIDs A list of strings containing the IDs to specify which sub-subforms to
     *        create
     */
    public DynamicSubFormController (IGame game,
                                     DynamicSFCFactory sfcFactory,
                                     List<String> subFormIDs) {
        setMyGame(game);
        setMySFCFactory(sfcFactory);
        setUpSubFormControllers(subFormIDs);
        setMyCurrentSFC(0);
        setUpSubFormViews(mySubFormControllers);
    }

    /**
     * Creates the sub-SFCs that will be dynamically swapped in and out based on
     * which type the user selects
     * 
     * @param subFormIDs A list of strings identifying which sub-SFCs to create
     */
    protected void setUpSubFormControllers (List<String> subFormIDs) {
        List<ISubFormController<SpriteDefinition>> subFormControllers = new ArrayList<>();
        for (String subFormID : subFormIDs) {
            ISubFormController<SpriteDefinition> sfc =
                    getMySFCFactory().createSpriteSubFormController(subFormID);
            subFormControllers.add(sfc);
        }

        setMySubFormControllers(subFormControllers);
    }

    /**
     * Sets which sub-SFC should be currently active (only used for initialization right now)
     * 
     * TODO: make consistent with view
     */
    protected void setMyCurrentSFC (int index) {
        setMyCurrentSubFormController(getMySubFormControllers().get(0));

    }

    /**
     * Creates the list of sub-subformviews from the sub-subformcontrollers
     * 
     * @param subFormControllers A list of sub-subformcontrollers
     */
    private void setUpSubFormViews (List<ISubFormController<SpriteDefinition>> subFormControllers) {
        mySubFormViews = new ArrayList<>();
        for (ISubFormController<SpriteDefinition> sfc : subFormControllers) {
            mySubFormViews.add(sfc.getSubFormView());
        }

    }

    /**
     * Event handler for changing selection. Changes which sub-SFC is currently shown
     * 
     * @param comboSelectionIndex The selected index
     */
    protected void changeSelection (int comboSelectionIndex) {
        myCurrentSubFormController = mySubFormControllers.get(comboSelectionIndex);
        myView.changeSubView(comboSelectionIndex);

    }

    @Override
    public void initializeFields () {
        for (ISubFormController<SpriteDefinition> subFormController : mySubFormControllers) {
            subFormController.initializeFields();
        }
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        myCurrentSubFormController.updateItem(item);

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    // Getters and Setters
    protected List<ISubFormView> getMySubFormViews () {
        return mySubFormViews;
    }

    protected void setMySubFormControllers (List<ISubFormController<SpriteDefinition>> subFormControllers) {
        this.mySubFormControllers = subFormControllers;
    }

    protected List<ISubFormController<SpriteDefinition>> getMySubFormControllers () {
        return this.mySubFormControllers;
    }

    protected void setMyCurrentSubFormController (ISubFormController<SpriteDefinition> subFormController) {
        this.myCurrentSubFormController = subFormController;

    }

    protected void setMyView (DynamicSubFormView view) {
        this.myView = view;
    }

    protected IGame getMyGame () {
        return myGame;
    }

    private void setMyGame (IGame myGame) {
        this.myGame = myGame;
    }

    public void setMySFCFactory (DynamicSFCFactory sfcFactory) {
        this.mySFCFactory = sfcFactory;

    }

    private DynamicSFCFactory getMySFCFactory () {
        return mySFCFactory;
    }

}
