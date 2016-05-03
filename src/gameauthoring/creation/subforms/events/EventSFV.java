package gameauthoring.creation.subforms.events;

import java.util.ResourceBundle;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.creation.subforms.fire.RemoveOption;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.ProfileDisplayIterator;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import splash.LocaleManager;


public class EventSFV extends SubFormView implements IEventSFV {

    private SingleChoiceEntryView<ProfileDisplay> myEvents;
    private ResourceBundle myLabel;
    private String myEventKey;
    private HBox myContainer;
    private RemoveOption myRemove;

    public EventSFV (ObservableList<ProfileDisplay> events, RemoveOption remove) {
        setResourceBundleAndKey();
        myRemove = remove;
        myEvents = new SingleChoiceEntryView<ProfileDisplay>(myEventKey,
                                                             events,
                                                             AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myEventKey = myLabel.getString("EventKey");
    }

    @Override
    public String getEventSelection () {
        return myEvents.getSelected().getProfile().getName().get();
    }

    @Override
    public void setEventSelection (String eventType) {
        myEvents.setSelected(new ProfileDisplayIterator().matchStringtoProfile(myEvents.getItems(),
                                                                               eventType));
    }

    @Override
    public Node draw () {
        getMyUIFactory().addStyling(myContainer, "Firer");
        return myContainer;
    }

    @Override
    protected void initView () {
        double spacing = getParser().parseDouble(getMyNumbers().getString("HBoxSpacing"));
        myContainer = getMyUIFactory().makeHBox(spacing,
                                                Pos.CENTER,
                                                myEvents.draw(),
                                                myRemove.draw());
    }

}
