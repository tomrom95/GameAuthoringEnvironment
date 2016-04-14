package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import engine.IGame;
import engine.SpriteGroup;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.definitions.TrackingMoverDefinition;
import engine.sprite.SpriteType;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.util.ErrorMessage;


/**
 * This class creates the controller to handle, manage, and interact with user data involving
 * tracking movement modules and projectiles
 * 
 * @author Dhrumil Timko
 *
 */

public class TrackingFireSubFormController implements ISubFormControllerSprite {

    private TrackingFireSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;

    private static Predicate<ModuleDefinition> findTrackingFirer () {
        return p -> p.getClass().equals(new TrackingFirerDefinition().getClass());
    }

    public TrackingFireSubFormController (IGame game) {
        myView = new TrackingFireSubFormView(game.getAuthorshipData().getMyCreatedGroups());
        myFormData = myView.getData();
        myGame = game;
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        try {
            TrackingFirerDefinition trackingFireDef = new TrackingFirerDefinition();
            trackingFireDef.setGame(myGame);
            Double waitTime =
                    Double.valueOf(myFormData.getValueProperty(myView.getWaitTimeKey()).get());
            trackingFireDef.setWaitTime(waitTime);
            trackingFireDef.setTargets(myView.getTargetsCoice().getSelected());
            item.addModule(trackingFireDef);
        }
        catch (NumberFormatException e) {
            ErrorMessage err = new ErrorMessage("Wait Time Must Be a Double");
            err.showError();
        }

    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        try {
            myView.getTargetsCoice().clearSelection();
            Object trackingDefinitionObject =
                    item.getModuleDefinitions().stream().filter(findTrackingFirer()).toArray()[0];
            TrackingFirerDefinition myDef = new TrackingFirerDefinition();
            myDef = (TrackingFirerDefinition) trackingDefinitionObject;
            myView.getTargetsCoice().setSelected(myDef.getTargets());
            myFormData.set(myView.getWaitTimeKey(), Double.toString(myDef.getWaitTime()));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            myFormData.set(myView.getWaitTimeKey(), "");
            myView.getTargetsCoice().clearSelection();
        }
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
