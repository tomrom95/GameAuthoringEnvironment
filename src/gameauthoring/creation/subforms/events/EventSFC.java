package gameauthoring.creation.subforms.events;

import java.util.ResourceBundle;
import engine.definitions.concrete.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.collections.ObservableList;


public class EventSFC implements ISubFormController<EventPackageDefinition> {

    private static final String PATH = "defaults/event_types";
    private ResourceBundle eventTypes = ResourceBundle.getBundle(PATH);
    private IEventSFV myView;
    private GameEvent myEvent;

    public EventSFC (GameEvent event) {
        myView = new EventSFV(getEvents());
        myEvent = event;
    }

    private ObservableList<ProfileDisplay> getEvents () {
        return new TypeFactory().getEffectTypes(eventTypes);
    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        myEvent.setEventType(new EventType(myView.getEventSelection()));
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

}
