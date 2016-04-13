package gameauthoring.conditiontab;

import engine.IGame;
import engine.conditions.ICondition;
import engine.conditions.OnClickCondition;
import engine.definitions.SpriteDefinition;
import engine.profile.IProfilable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OnClickPopUp extends ConditionPopUp{

   
    private IGame myGame;
    
    public OnClickPopUp(IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        myGame = game;
        initStage();
    }

    @Override
    protected ICondition createCondition () {
        return new OnClickCondition(myGame, null, null, null);
    }

    @Override
    protected void initializeDisplay () {
        add(getHBox(), 0, 0);
        
    }

    private Node getHBox () {
       HBox hbox = new HBox(CUSHION);
       hbox.getChildren().add(getCombo("Target", myGame.getAuthorshipData().getMyCreatedGroups().getItems()));
       hbox.getChildren().add(getCombo("Self Effects", myGame.getAuthorshipData().getMyCreatedGroups().getItems()));
       hbox.getChildren().add(getCombo("Other Group", myGame.getAuthorshipData().getMyCreatedGroups().getItems()));
       hbox.getChildren().add(getCombo("Group Effects", myGame.getAuthorshipData().getMyCreatedGroups().getItems()));
       hbox.getChildren().add(getCombo("Global Effects", myGame.getAuthorshipData().getMyCreatedGroups().getItems()));
       
       return hbox;
       
    }

    private Node getCombo (String label, ObservableList<? extends IProfilable> options) {
        VBox vbox = new VBox ();
        ComboBox<? extends IProfilable> box = new ComboBox<>(options);   
        vbox.getChildren().addAll(new Label(label), box);
        return vbox;
    }
    
    
}
