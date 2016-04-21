package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.IEventPackage;
import engine.definitions.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import javafx.scene.control.ComboBox;
import util.BundleOperations;


public class EndOptions {

    ResourceBundle myEndOptions = ResourceBundle.getBundle("defaults/end_event_options");
    private ComboBox<String> myEventChoices;

    public EndOptions () {
        myEventChoices = new ComboBox(BundleOperations.getKeysAsObservable(myEndOptions));
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
