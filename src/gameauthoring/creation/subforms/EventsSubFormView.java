package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.events.EffectSubFormView;
import gameauthoring.creation.subforms.events.EventSubFormView;
import gameauthoring.shareddata.IDefinitionCollection;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class EventsSubFormView implements ISubFormView{
    
    private EventSubFormView myEventView;
    private EffectSubFormView myEffectView;
    private IFormDataManager myEffectData;
    private IFormDataManager myEventData;
    
    public EventsSubFormView (IDefinitionCollection<AttributeDefinition> myCreatedAttributes) {
        myEventView = new EventSubFormView();
        myEffectView = new EffectSubFormView(myCreatedAttributes);
        myEventData = null;
        myEffectData = null;
    }

    @Override
    public Node draw () {
        HBox row = new HBox(5);
        row.getChildren().add(createComboBox(row));
        row.getChildren().add(new HBox(5));
        return row;
    }

    private Node createComboBox (HBox row) {
        ComboBox<String> box = new ComboBox<>();
        box.getItems().add("Event");
        box.getItems().add("Effect");
        box.setOnAction(e -> addSubForm(row, box.getSelectionModel().getSelectedItem()));
        return box;
    }

    private void addSubForm (HBox row, String selectedItem) {
        if (selectedItem == "Event") {
            row.getChildren().set(1, myEventView.draw());
            myEventData = myEventView.getData();
            myEffectData = null;
        } else {
            row.getChildren().set(1, myEffectView.draw());
            myEffectData = myEffectView.getData();
            myEventData = null;
        }
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IFormDataManager getData () {
        return null;
    }
    
    public IFormDataManager getEventData() {
        return myEventData;
    }
    
    public IFormDataManager getEffectData() {
        return myEffectData;
    }
    
    public EventSubFormView getEventView () {
        return myEventView;
    }
    public EffectSubFormView getEffectView () {
        return myEffectView;
    }

}
