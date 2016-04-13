package gameauthoring.conditiontab;


import engine.IGame;
import engine.OnCollisionCondition;
import engine.conditions.ICondition;
import engine.profile.IProfilable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OnCollisionPopUp extends ConditionPopUp {

    
    private IGame myGame;
    
    public OnCollisionPopUp (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
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
        hbox.getChildren().add(getCombo("Group A", getSprites()));
        hbox.getChildren().add(getCombo("GroupB", getSprites()));
        hbox.getChildren().add(getCombo("Group A Effects", null));
        hbox.getChildren().add(getCombo("Group B Effects", null));
        hbox.getChildren().add(getCombo("Global Effects", null));
        return hbox;
        
     }
    
    
    
    private ObservableList<? extends IProfilable> getSprites () {
        return myGame.getAuthorshipData().getMyCreatedGroups().getItems();
    }

    private Node getCombo (String label,  ObservableList<? extends IProfilable> options) {
        VBox vbox = new VBox ();
        ComboBox<? extends IProfilable> box = new ComboBox<>(options);   
        vbox.getChildren().addAll(new Label(label), box);
        return vbox;
    }
    

    @Override
    protected ICondition createCondition () {
        return new OnCollisionCondition(myGame, null, null, null, null);
    }

}
