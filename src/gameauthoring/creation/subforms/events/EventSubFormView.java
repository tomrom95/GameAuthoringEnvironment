package gameauthoring.creation.subforms.events;

import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public class EventSubFormView extends SubFormView {

    private SingleChoiceEntryView<ProfileDisplay> myEvents;
    private String myEventKey = "Event type: ";

    public EventSubFormView (ObservableList<ProfileDisplay> events) {
        myEvents = new SingleChoiceEntryView<ProfileDisplay>(myEventKey,
                                                             events,
                                                             AuthoringView.DEFAULT_ENTRYVIEW);
    }
    
    public String getEventSelection () {
        return myEvents.getSelected().getProfile().getName().get();
    }

    @Override
    public Node draw () {
        return myEvents.draw();
    }

    @Override
    protected void initView () {
        
    }

}
