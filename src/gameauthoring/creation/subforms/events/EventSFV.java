package gameauthoring.creation.subforms.events;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class EventSFV extends SubFormView {

    private SingleChoiceEntryView<ProfileDisplay> myEvents;
    private ResourceBundle myLabel;
    private String myEventKey;
    private TextField myName;

    public EventSFV (ObservableList<ProfileDisplay> events) {
        setResourceBundleAndKey();
        myEvents = new SingleChoiceEntryView<ProfileDisplay>(myEventKey,
                                                             events,
                                                             AuthoringView.DEFAULT_ENTRYVIEW);
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myEventKey = myLabel.getString("EventKey");
    }

    public String getEventSelection () {
        return myEvents.getSelected().getProfile().getName().get();
    }

    @Override
    public Node draw () {
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(8);
        myName = new TextField();
        vbox.getChildren().add(new Label(myLabel.getString("EventName")));
        vbox.getChildren().add(myName);
        myName.setPromptText(myLabel.getString("EnterText"));
        hbox.getChildren().add(vbox);
        hbox.getChildren().add(myEvents.draw());
        return hbox;
    }

    @Override
    protected void initView () {

    }

    public String getName () {
        return myName.getText();
    }

    public void setEventSelection (String eventType) {
        // TODO this is a hacky fix
        for (ProfileDisplay pd : myEvents.getItems()) {
            if (pd.getProfile().getName().get().equals(eventType)) {
                myEvents.setSelected(pd);
            }
        }
    }

    public void setName (String name) {
        myName.setText(name);
    }

}
