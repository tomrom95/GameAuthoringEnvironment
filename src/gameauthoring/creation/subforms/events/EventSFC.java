package gameauthoring.creation.subforms.events;

import java.util.ResourceBundle;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.fire.RemovableEventSFC;
import javafx.collections.ObservableList;


public class EventSFC extends RemovableEventSFC {

    private static final String PATH = "defaults/event_types";
    private ResourceBundle eventTypes = ResourceBundle.getBundle(PATH);
    private IEventSFV myView;
    private GameEvent myEvent;

    public EventSFC (IGame game, EventChoiceSFC sfc) {
        super(sfc);
        init(game, new GameEvent(null));
       
    }
    public EventSFC (IGame game, EventChoiceSFC sfc, GameEvent event) {
        super(sfc);
        init(game, event);
    }
    private void init(IGame game, GameEvent event) {
     
        myView = new EventSFV(getEvents(), getRemoveMenu());
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
    public void initializeFields (EventPackageDefinition item) {

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
