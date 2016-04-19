package gameauthoring.creation.subforms;

import java.util.List;
import engine.definitions.SpriteDefinition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public abstract class DynamicSubFormController implements ISubFormControllerSprite {
    private DynamicSubFormView myView;
    private ObservableList<ISubFormView> mySubFormViews;
    private List<ISubFormController<SpriteDefinition>> mySubFormControllers;
    private ISubFormController<SpriteDefinition> myCurrentSubFormController;

    public DynamicSubFormController () {
        setUpSubFormControllers();
        setUpSubFormViews(mySubFormControllers);
    }

    protected abstract void setUpSubFormControllers ();

    protected abstract void setMyCurrentSFC ();

    private void setUpSubFormViews (List<ISubFormController<SpriteDefinition>> subFormControllers) {
        mySubFormViews = FXCollections.observableArrayList();
        for (ISubFormController<SpriteDefinition> sfc : subFormControllers) {
            mySubFormViews.add(sfc.getSubFormView());
        }

    }

    protected void changeType (int comboSelectionIndex) {
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

}
