package gameauthoring.creation.subforms.events;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.IGame;
import engine.definitions.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EventSubFormController implements ISubFormController<EventPackageDefinition> {
    private static final String PATH = "defaults/event_types";
    private ResourceBundle eventTypes = ResourceBundle.getBundle(PATH);
    private EventSubFormView myView;

    public EventSubFormController () {
        myView = new EventSubFormView(getEvents());
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
    public void initializeFields () {
        
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}