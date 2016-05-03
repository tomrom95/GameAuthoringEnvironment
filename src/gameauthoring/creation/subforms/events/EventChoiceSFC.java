package gameauthoring.creation.subforms.events;

import java.util.List;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;
import util.BundleOperations;


public class EventChoiceSFC extends MultiOptionSFC<EventPackageDefinition> {

    private static final String EVENT_KEY = "Events";

    public EventChoiceSFC (IGame game) {
        super(game);
        setMyOptions(BundleOperations.getPropertyValueAsList(EVENT_KEY, getMyOptionsFile()));
        setMyView(new EventChoiceSFV(getMyOptions(), EVENT_KEY));
        setActions();
    }

    @Override
    protected List<? extends Object> getList (EventPackageDefinition item) {
        return item.getMyEventsList();
    }

    @Override
    protected void resetContents (EventPackageDefinition item) {
        item.getMyEventsList().clear();
    }

}
