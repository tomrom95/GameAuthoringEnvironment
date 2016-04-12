package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.profile.IProfilable;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.entryviews.ImageEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class SelectAttributeSubFormView extends SubFormView{

   private String myAttributesKey = "Attributes :";
   private SingleChoiceEntryView<AttributeDefinition> myAttributeSelector;
   
    public SelectAttributeSubFormView (IDefinitionCollection<AttributeDefinition> attributes) {
        myAttributeSelector = new SingleChoiceEntryView<AttributeDefinition>(myAttributesKey,attributes.getItems(), 20);
    }

    @Override
    public Node draw () {
        return myAttributeSelector.draw();
    }
    
    public SingleChoiceEntryView<AttributeDefinition> getEntryView(){
        return myAttributeSelector;
    }
    


    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IFormDataManager getData () {
        // TODO Auto-generated method stub
        return null;
    }

}
