package gameauthoring.creation.subforms.events;

import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class EventSFV extends SubFormView implements IEventSFV {

    private SingleChoiceEntryView<ProfileDisplay> myEvents;
    private String myEventKey = "Event type: ";

    public EventSFV (ObservableList<ProfileDisplay> events) {
        myEvents = new SingleChoiceEntryView<ProfileDisplay>(myEventKey,
                                                             events,
                                                             AuthoringView.DEFAULT_ENTRYVIEW);
    }
    
    @Override
    public String getEventSelection () {
        return myEvents.getSelected().getProfile().getName().get();
    }   
    
    @Override
    public void setEventSelection (String eventType) {
        for (ProfileDisplay pd : myEvents.getItems()){
            if(pd.getProfile().getName().get().equals(eventType)){
                myEvents.setSelected(pd);
            }
        }
    }
    
    @Override
    public Node draw () {
        return myEvents.draw();
    }
    
    @Override
    protected void initView () {
        
    }

}
