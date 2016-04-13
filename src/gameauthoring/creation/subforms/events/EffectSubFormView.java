package gameauthoring.creation.subforms.events;

import java.util.ArrayList;
import java.util.Arrays;
import engine.definitions.AttributeDefinition;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class EffectSubFormView extends SubFormView{
    
    private SingleChoiceEntryView<AttributeDefinition> myAttribute;
    private IEntryView myLength;
    private IEntryView myValue;
    
    public EffectSubFormView (IDefinitionCollection<AttributeDefinition> myCreatedAttributes) {
        myAttribute = new SingleChoiceEntryView<AttributeDefinition>(
                "Attribute", myCreatedAttributes.getItems(), 5);
        myLength = new TextEntryView("Length",this.getData(), 20, 150, 30);
        myValue = new TextEntryView("Amount", this.getData(), 20, 150, 30);
        super.setMyEntryViews( new ArrayList<IEntryView>(Arrays.asList(myAttribute, myLength, myValue)));
    }

    @Override
    public void update () {
        
    }
    
    public AttributeDefinition getAttribute () {
        return myAttribute.getSelected();
    }

    @Override
    public Node draw () {
        HBox box = new HBox(5);
        box.getChildren().add(myAttribute.draw());
        box.getChildren().add(myLength.draw());
        box.getChildren().add(myValue.draw());
        return box;
    }

}
