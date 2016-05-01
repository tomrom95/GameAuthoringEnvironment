package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IEventPackage;
import engine.definitions.concrete.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import javafx.scene.control.ComboBox;
import util.BundleOperations;


/**
 * Displays a combo box of ending condition options (win, lose, etc.)
 * Used in composition of many sub condition views the display this type of combo box
 * 
 * @author RyanStPierre
 *
 */

public class EndOptions {

    private ResourceBundle myEndOptions = ResourceBundle.getBundle("defaults/end_event_options");
    private ComboBox<String> myEventChoices;

    public EndOptions () {
        myEventChoices = new ComboBox<>(BundleOperations.getValuesAsObservable(myEndOptions));
    }

    public IEventPackage getGlobal () {
        EventPackageDefinition global = new EventPackageDefinition();
        global.getMyEventsList().add(new GameEvent(getEventType()));
        return global.create();
    }

    private EventType getEventType () {
        return new EventTypeFactory()
                .interpret(myEventChoices.getSelectionModel().getSelectedItem());
    }

    public ComboBox<String> getBox () {
        return myEventChoices;
    }

}
