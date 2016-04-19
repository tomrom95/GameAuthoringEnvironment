package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.List;
import engine.IGame;
import engine.definitions.SpriteDefinition;


public abstract class DynamicSubFormController implements ISubFormControllerSprite {
    private DynamicSubFormView myView;
    private List<ISubFormView> mySubFormViews;
    private List<ISubFormController<SpriteDefinition>> mySubFormControllers;
    private ISubFormController<SpriteDefinition> myCurrentSubFormController;
    private IGame myGame;
    private DynamicSFCFactory mySFCFactory;
    private List<String> mySubFormIDs;

    public DynamicSubFormController (IGame game,
                                     DynamicSFCFactory sfcFactory,
                                     List<String> subFormIDs) {
        setMyGame(game);
        setMySFCFactory(sfcFactory);
        setMySubFormIDs(subFormIDs);
        setUpSubFormControllers();
        setMyCurrentSFC();
        setUpSubFormViews(mySubFormControllers);
    }

    protected void setUpSubFormControllers () {
        List<ISubFormController<SpriteDefinition>> subFormControllers = new ArrayList<>();
        for (String subFormID : mySubFormIDs) {
            ISubFormController<SpriteDefinition> sfc =
                    getMySFCFactory().createSpriteSubFormController(subFormID);
            subFormControllers.add(sfc);
        }

        setMySubFormControllers(subFormControllers);
    }

    protected abstract void setMyCurrentSFC ();

    private void setUpSubFormViews (List<ISubFormController<SpriteDefinition>> subFormControllers) {
        mySubFormViews = new ArrayList<>();
        for (ISubFormController<SpriteDefinition> sfc : subFormControllers) {
            mySubFormViews.add(sfc.getSubFormView());
        }

    }

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

    private void setMySubFormIDs (List<String> subFormIDs) {
        this.mySubFormIDs = subFormIDs;

    }

    public void setMySFCFactory (DynamicSFCFactory sfcFactory) {
        this.mySFCFactory = sfcFactory;

    }

    private DynamicSFCFactory getMySFCFactory () {
        return mySFCFactory;
    }

}
