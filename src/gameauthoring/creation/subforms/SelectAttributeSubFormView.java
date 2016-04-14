package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import javafx.scene.Node;

public class SelectAttributeSubFormView extends SubFormView{

   private String myAttributesKey = "Attributes :";
   private MultiChoiceEntryView<AttributeDefinition> myAttributeSelector;
   
    public SelectAttributeSubFormView (IDefinitionCollection<AttributeDefinition> attributes) {
        myAttributeSelector = new MultiChoiceEntryView<AttributeDefinition>(myAttributesKey,attributes.getItems(), 20);
    }

    @Override
    public Node draw () {
        return myAttributeSelector.draw();
    }
    
    public MultiChoiceEntryView<AttributeDefinition> getEntryView(){
        return myAttributeSelector;
    }

    @Override
    public IFormDataManager getData () {
        // TODO Auto-generated method stub
        return null;
    }

}
