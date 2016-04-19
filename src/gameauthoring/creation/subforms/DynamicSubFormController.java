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
    private SubFormControllerFactory mySFCFactory;
    private IGame myGame;


    public DynamicSubFormController (SubFormControllerFactory sfcFactory, IGame game) {
        setMyGame(game);
        setMySFCFactory(sfcFactory);
        setUpSubFormControllers();
        setMyCurrentSFC();
        setUpSubFormViews(mySubFormControllers);
    }

    protected abstract void setUpSubFormControllers ();

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
    
    protected List<ISubFormView> getMySubFormViews(){
        return mySubFormViews;
    }
    protected void setMySubFormControllers (List<ISubFormController<SpriteDefinition>> subFormControllers) {
        this.mySubFormControllers = subFormControllers;
    }
    protected List<ISubFormController<SpriteDefinition>> getMySubFormControllers(){
        return this.mySubFormControllers;
    }
    protected void setMyCurrentSubFormController (ISubFormController<SpriteDefinition> subFormController) {
        this.myCurrentSubFormController = subFormController;
        
    }
    protected void setMyView(DynamicSubFormView view){
        this.myView = view;
    }

    protected SubFormControllerFactory getMySFCFactory () {
        return mySFCFactory;
    }

    private void setMySFCFactory (SubFormControllerFactory mySFCFactory) {
        this.mySFCFactory = mySFCFactory;
    }

    protected IGame getMyGame () {
        return myGame;
    }

    private void setMyGame (IGame myGame) {
        this.myGame = myGame;
    }


}
