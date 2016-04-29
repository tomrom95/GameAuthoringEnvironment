package gameauthoring.creation.subforms.events;

import java.util.ResourceBundle;
import engine.definitions.concrete.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.fire.RemovableEventSFC;
import javafx.collections.ObservableList;


public class EventSFC extends RemovableEventSFC {

    private static final String PATH = "defaults/event_types";
    private ResourceBundle eventTypes = ResourceBundle.getBundle(PATH);
    private IEventSFV myView;
    private GameEvent myEvent;

    public EventSFC (GameEvent event, EventChoiceSFC sfc) {
        super(sfc);
        myView = new EventSFV(getEvents());
        myEvent = event;
    }

    private ObservableList<ProfileDisplay> getEvents () {
        return new TypeFactory().getEffectTypes(eventTypes);
    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        myEvent = new GameEvent(new EventType(myView.getEventSelection()));
        item.getMyEventsList().add(myEvent);
    }

    @Override
    public void populateViewsWithData (EventPackageDefinition item) {
        myView.setEventSelection(myEvent.getEventType().getType());
    }

    @Override
    public void initializeFields () {

    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public GameEvent getModuleDefinition () {
        return myEvent;
    }

}
