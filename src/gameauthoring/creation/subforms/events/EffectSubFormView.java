package gameauthoring.creation.subforms.events;

import engine.definitions.AttributeDefinition;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


public class EffectSubFormView extends SubFormView {

    private SingleChoiceEntryView<AttributeDefinition> myAttribute;
    private IEntryView myLength;
    private IEntryView myValue;
    private String myLengthKey = "Length: ";
    private String myValueKey = "Amount: ";
    private String myAttributeKey = "Attribute: ";
    private HBox myContainer;

    public EffectSubFormView (IDefinitionCollection<AttributeDefinition> myCreatedAttributes) {
        myAttribute =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeKey,
                                                               myCreatedAttributes.getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myLength =
                new TextEntryView(myLengthKey, this.getData(), 150, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
        myValue =
                new TextEntryView(myValueKey, this.getData(), 150, 30,
                                  AuthoringView.DEFAULT_ENTRYVIEW);
    }

    @Override
    protected void initView () {
        myContainer = new HBox();
        myContainer.getChildren().add(myAttribute.draw());
        myContainer.getChildren().add(myLength.draw());
        myContainer.getChildren().add(myValue.draw());
    }

    public AttributeDefinition getAttribute () {
        return myAttribute.getSelected();
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
