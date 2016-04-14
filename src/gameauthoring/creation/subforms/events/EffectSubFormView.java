package gameauthoring.creation.subforms.events;

import java.util.ArrayList;
import java.util.Arrays;
import engine.definitions.AttributeDefinition;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.TextEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class EffectSubFormView extends SubFormView{
    
    private ListView<AttributeDefinition> myAttribute;
    private IEntryView myLength;
    private IEntryView myValue;
    
    public EffectSubFormView (IDefinitionCollection<AttributeDefinition> myCreatedAttributes) {
        myAttribute = new ListView<AttributeDefinition>();
        myAttribute.setItems(myCreatedAttributes.getItems());
        myAttribute.setCellFactory(c -> new ProfileCellView<AttributeDefinition>());
        myAttribute.setMaxHeight(50);
        myLength = new TextEntryView("Length",this.getData(), 20, 150, 30);
        myValue = new TextEntryView("Amount", this.getData(), 20, 150, 30);
        super.setMyEntryViews( new ArrayList<IEntryView>(Arrays.asList(myLength, myValue)));
    }
    
    public AttributeDefinition getAttribute () {
        return myAttribute.getSelectionModel().getSelectedItem();
    }

    @Override
    public Node draw () {
        HBox box = new HBox(5);
        box.getChildren().add(myAttribute);
        box.getChildren().add(myLength.draw());
        box.getChildren().add(myValue.draw());
        return box;
    }

}
