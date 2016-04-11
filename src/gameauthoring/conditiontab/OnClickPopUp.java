package gameauthoring.conditiontab;

import java.util.List;
import engine.ICondition;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OnClickPopUp extends ConditionPopUp{

    private static final double CUSHION = 10;

    private IGame myGame;
    
    public OnClickPopUp(ObservableList<ICondition> conditionList, IGame game) {
        super(conditionList);
        myGame = game;
        initStage();
    }

    @Override
    protected ICondition createCondition () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void initializeDisplay () {
        add(getHBox(), 0, 0);
        
    }

    private Node getHBox () {
       HBox hbox = new HBox(CUSHION);
       hbox.getChildren().add(getCombo("Target", myGame.getAuthorshipData().getCreatedSprites()));
       hbox.getChildren().add(getCombo("Tayla", null));
       
       return hbox;
       
    }

    private Node getCombo (String label, ObservableList<SpriteDefinition> options) {
        VBox vbox = new VBox ();
        ComboBox<SpriteDefinition> box = new ComboBox<>(options);   
        vbox.getChildren().addAll(new Label(label), box);
        return vbox;
    }
    
    
}
