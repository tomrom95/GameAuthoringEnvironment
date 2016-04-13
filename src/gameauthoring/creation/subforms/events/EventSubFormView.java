package gameauthoring.creation.subforms.events;

import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class EventSubFormView implements ISubFormView{

    @Override
    public Node draw () {
        ComboBox<String> box = new ComboBox<String>();
        box.getItems().add("Death");
        return box;
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
