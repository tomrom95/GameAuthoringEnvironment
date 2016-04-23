package gameauthoring.creation.subforms;

import java.util.List;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;


/**
 * Implementation of ISelectAttributeSFV with MultiChoiceEntryView display
 * 
 * @author Joe Lilien
 *
 */
public class SelectAttributeSFV extends SubFormView implements ISelectAttributeSFV {

    private String myAttributesKey = "Attributes :";
    private MultiChoiceEntryView<AttributeDefinition> myAttributeSelector;

    public SelectAttributeSFV (IDefinitionCollection<AttributeDefinition> attributes) {
        myAttributeSelector =
                new MultiChoiceEntryView<AttributeDefinition>(myAttributesKey,
                                                              attributes.getItems(), 400, 200,
                                                              AuthoringView.DEFAULT_ENTRYVIEW);
    }

    @Override
    public Node draw () {
        return myAttributeSelector.draw();
    }

    @Override
    public List<AttributeDefinition> getSelectedAttributes () {
        return myAttributeSelector.getSelected();
    }

    @Override
    protected void initView () {

    }

}
