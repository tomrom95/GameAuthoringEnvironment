package gameauthoring.creation.subforms.events;

import java.util.List;
import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.AuthorshipData;
import engine.definitions.concrete.AttributeDefinition;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class EffectSFV extends SubFormView {

    private SingleChoiceEntryView<AttributeDefinition> myAttribute;
    private SingleChoiceEntryView<ProfileDisplay> myEffectType;
    private IEntryView myLength;
    private IEntryView myValue;
    private TextField myName;
    private ResourceBundle myLabel;
    private String myLengthKey;
    private String myValueKey;
    private String myAttributeKey;
    private String myTypeKey;
    private VBox myContainer;

    public EffectSFV (AuthorshipData data, List<String> effectTypes) {
        setResourceBundleAndKey();
        // TODO: extract new IProfileSFV implementation that just has a textfield for name so we can
        // reuse this code
        myName = new TextField();
        myName.setPromptText(myLabel.getString("EnterName"));

        myEffectType = new SingleChoiceEntryView<ProfileDisplay>(myTypeKey,
                                                                 createEffectTypeList(effectTypes),
                                                                 AuthoringView.DEFAULT_ENTRYVIEW);
        ObservableList<AttributeDefinition> definitions = createAttributeList(data);
        myAttribute =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeKey,
                                                               definitions,
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myValue =
                new TextEntryView(myValueKey, this.getData(), 100, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
        myLength =
                new TextEntryView(myLengthKey, this.getData(), 100, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myLengthKey = myLabel.getString("LengthKey");
        myValueKey = myLabel.getString("ValueKey");
        myAttributeKey = myLabel.getString("AttributeKey");
        myTypeKey = myLabel.getString("TypeKey");
    }

    private ObservableList<AttributeDefinition> createAttributeList (AuthorshipData data) {
        ObservableList<AttributeDefinition> defs = FXCollections.observableArrayList();
        defs.addAll(data.getMyCreatedAttributes().getItems());
        defs.addAll(data.getMyCreatedGlobals().getItems());
        return defs;
    }

    private ObservableList<ProfileDisplay> createEffectTypeList (List<String> effectTypes) {
        ObservableList<ProfileDisplay> types = FXCollections.observableArrayList();
        effectTypes.stream()
                .forEach(e -> types.add(new ProfileDisplay(e)));
        return types;
    }

    @Override
    protected void initView () {
        myContainer = new VBox(4);
        HBox hbox = new HBox(10);
        myName.setMaxWidth(150);
        myContainer.getChildren().add(myName);
        hbox.getChildren().add(myEffectType.draw());
        hbox.getChildren().add(myAttribute.draw());
        hbox.getChildren().add(myValue.draw());
        hbox.getChildren().add(myLength.draw());
        myContainer.getChildren().add(hbox);
    }

    public AttributeDefinition getAttribute () {
        return myAttribute.getSelected();
    }

    public String getEffectType () {
        return myEffectType.getSelected().getProfile().getName().get();
    }

    public String getName () {
        return myName.getText();
    }

    public String getValueKey () {
        return myValueKey;
    }

    public String getLengthKey () {
        return myLengthKey;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    public void setName (String name) {
        myName.setText(name);
    }

    public void setEventSelection (String effectType) {
        // TODO this is a hacky fix
        for (ProfileDisplay pd : myEffectType.getItems()) {
            if (pd.getProfile().getName().get().equals(effectType)) {
                myEffectType.setSelected(pd);
            }
        }
    }

}
