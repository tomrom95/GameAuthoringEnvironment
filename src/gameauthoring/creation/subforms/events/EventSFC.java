package gameauthoring.creation.subforms.events;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EventSFC implements ISubFormController<EventPackageDefinition> {
    private static final String NAME = "Event"; //TODO maybe put in resource file

    private static final String PATH = "defaults/event_types";
    private ResourceBundle eventTypes = ResourceBundle.getBundle(PATH);
    private EventSFV myView;
    
    

    public EventSFC () {
        myView = new EventSFV(getEvents());
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
        
        // Default profile instead of profileSFC
        item.getProfile().getName().set(myView.getName());
        item.getProfile().getDescription().set(myView.getEventSelection());
        
        
        GameEvent event = new GameEvent(new EventType(myView.getEventSelection()));
        
        //TODO: need to find and replace instead of adding on each save
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
