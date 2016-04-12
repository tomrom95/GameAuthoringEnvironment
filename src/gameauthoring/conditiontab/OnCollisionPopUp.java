package gameauthoring.conditiontab;

import engine.ICondition;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OnCollisionPopUp extends ConditionPopUp {

    
    private IGame myGame;
    
    public OnCollisionPopUp (ObservableList<ICondition> conditionList,IGame game) {
        super(conditionList);
        myGame = game;
        initStage();
    }

    @Override
    protected void initializeDisplay () {
        add(getHBox(), 0, 0);

    }
    
    private Node getHBox () {
        HBox hbox = new HBox(CUSHION);
        //Connections
        hbox.getChildren().add(getCombo("Group A", myGame.getAuthorshipData().getCreatedSprites()));
        hbox.getChildren().add(getCombo("GroupB", null));
        hbox.getChildren().add(getCombo("Group A Effects", null));
        hbox.getChildren().add(getCombo("Group B Effects", null));
        hbox.getChildren().add(getCombo("Global Effects", null));
        
        return hbox;
        
     }
    
    private Node getCombo (String label, ObservableList<SpriteDefinition> options) {
        VBox vbox = new VBox ();
        ComboBox<SpriteDefinition> box = new ComboBox<>(options);   
        vbox.getChildren().addAll(new Label(label), box);
        return vbox;
    }
    

    @Override
    protected ICondition createCondition () {
        // TODO Auto-generated method stub
        return null;
    }

}
