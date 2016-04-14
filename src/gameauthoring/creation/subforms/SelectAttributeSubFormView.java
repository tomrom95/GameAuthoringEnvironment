package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;


public class SelectAttributeSubFormView extends SubFormView {

    private String myAttributesKey = "Attributes :";
    private MultiChoiceEntryView<AttributeDefinition> myAttributeSelector;

    public SelectAttributeSubFormView (IDefinitionCollection<AttributeDefinition> attributes) {
        myAttributeSelector =
                new MultiChoiceEntryView<AttributeDefinition>(myAttributesKey,
                                                              attributes.getItems(), 400, 200,
                                                              AuthoringView.DEFAULT_ENTRYVIEW);
    }

    @Override
    public Node draw () {
        return myAttributeSelector.draw();
    }

    public MultiChoiceEntryView<AttributeDefinition> getEntryView () {
        return myAttributeSelector;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
