package gameauthoring.characters;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


/**
 * Allows Users to select multiple items from a given list in a choice box fashion to assoicate with
 * a given label
 * 
 * NOTE: this implementation will be easier if we are allowed to import a third party library. I
 * will ask professor Duvall about it on Thursday
 * 
 * @author JoeLilien
 *
 */
public class MultiChoiceEntryView implements IEntryView {
    private String myLabel;
    private HBox myContainer;
    
    public MultiChoiceEntryView(String label, double spacing, List<Object> choices){
        this.myLabel = label;
        this.myContainer = new HBox(spacing);
        myContainer.getChildren().add(new Label(myLabel));
    }

    @Override
    public Node draw () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public FormData getData () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void populateWithData (FormData data) {
        // TODO Auto-generated method stub

    }


}
