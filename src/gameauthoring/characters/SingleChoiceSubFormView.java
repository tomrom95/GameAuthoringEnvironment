package gameauthoring.characters;

import java.util.List;
import engine.ISprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SingleChoiceSubFormView implements SubFormView{
    private String myLabel;
    private SubFormController myController;
    private HBox myContainer = new HBox(20);
    private ChoiceBox<?> myChoices;
    
    public SingleChoiceSubFormView(String label, SubFormController controller, List<Object> choices){
        ObservableList<Object> obsChoices = FXCollections.observableList(choices);
        this.myLabel = label;
        this.myController = controller;
        this.myChoices = new ChoiceBox<Object>(obsChoices);
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myChoices);
    }


    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FormData getData () {
        String key = myLabel;
        Object value = myChoices.getSelectionModel().getSelectedItem();
        return null;
    }

    @Override
    public void populateWithData (ISprite s) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public SubFormController getSubFormController () {
        return this.myController;
    }

    @Override
    public void setSubFormController (SubFormController controller) {
        this.myController = controller;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
