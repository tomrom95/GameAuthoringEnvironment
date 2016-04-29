package gameauthoring.creation.subforms.events;

import java.util.List;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;
import util.BundleOperations;

public class EventChoiceSFC extends MultiOptionSFC<EventPackageDefinition>{
    
    private String eventKey = "EVENTS";

    public EventChoiceSFC (IGame game) {
        super(game);
        setMyOptions(BundleOperations.getPropertyValueAsList(eventKey, getMyOptionsFile()));
        setMyView(new EventChoiceSFV(getMyOptions()));
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
