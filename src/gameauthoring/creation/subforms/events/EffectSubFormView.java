package gameauthoring.creation.subforms.events;

import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


public class EffectSubFormView extends SubFormView {

    private SingleChoiceEntryView<AttributeDefinition> myAttribute;
    private SingleChoiceEntryView<ProfileDisplay> myEffectType;
    private IEntryView myLength;
    private IEntryView myValue;
    private String myLengthKey = "For length in time: ";
    private String myValueKey = "By amount: ";
    private String myAttributeKey = "Affect Attribute ";
    private String myTypeKey = "Effect type: ";
    private HBox myContainer;

    public EffectSubFormView (IDefinitionCollection<AttributeDefinition> myCreatedAttributes,
                              List<String> effectTypes) {
        myEffectType = new SingleChoiceEntryView<ProfileDisplay> (myTypeKey,
                                                                  createEffectTypeList(effectTypes),
                                                                  AuthoringView.DEFAULT_ENTRYVIEW);
        myAttribute =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeKey,
                                                               myCreatedAttributes.getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myValue =
                new TextEntryView(myValueKey, this.getData(), 100, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
        myLength =
                new TextEntryView(myLengthKey, this.getData(), 100, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    private ObservableList<ProfileDisplay> createEffectTypeList (List<String> effectTypes) {
        ObservableList<ProfileDisplay> types = FXCollections.observableArrayList();
        effectTypes.stream()
                   .forEach(e -> types.add(new ProfileDisplay(e)));
        return types;
    }

    @Override
    protected void initView () {
        myContainer = new HBox(5);
        myContainer.getChildren().add(myEffectType.draw());
        myContainer.getChildren().add(myAttribute.draw());
        myContainer.getChildren().add(myValue.draw());
        myContainer.getChildren().add(myLength.draw());
    }

    public AttributeDefinition getAttribute () {
        return myAttribute.getSelected();
    }
    
    public String getEffectType () {
        return myEffectType.getSelected().getProfile().getName().get();
    }
    
    public String getValueKey () {
        return myLengthKey;
    }
    
    public String getLengthKey () {
        return myLengthKey;
    }

    @Override
    public Node draw () {
        System.out.println("Here" + myContainer.getChildren().size());
        return myContainer;
    }

}
