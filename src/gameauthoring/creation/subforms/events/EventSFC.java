package gameauthoring.creation.subforms.events;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.definitions.concrete.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.collections.FXCollections;
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
        List<ProfileDisplay> list = Collections.list(eventTypes.getKeys())
                .stream()
                .map(s -> new ProfileDisplay(s))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(list);

    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        GameEvent event = new GameEvent(new EventType(myView.getEventSelection()));
        item.getMyEventsList().add(event);
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
