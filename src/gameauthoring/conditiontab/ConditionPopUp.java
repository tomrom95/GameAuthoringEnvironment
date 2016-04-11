package gameauthoring.conditiontab;

import engine.ICondition;
import engine.IGame;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class ConditionPopUp {

    private static final double POP_UP_WIDTH = 700;
    private static final double POP_UP_HEIGHT = 300;
    private Stage myStage;
    private GridPane myGroup;
    private ObservableList<ICondition> myList;
    
    public ConditionPopUp(ObservableList<ICondition> conditionList) {
        myList = conditionList;
    }
    

    protected void initStage () {
        myStage = new Stage ();
        myGroup = new GridPane ();
        myStage.setScene(new Scene(myGroup, POP_UP_WIDTH, POP_UP_HEIGHT, Color.ALICEBLUE)); 
        initializeDisplay();
        myGroup.add(createButton(), 0, 1);
        
    }
    
    protected abstract void initializeDisplay ();

    private Node createButton () {
        Button button = new Button("Create");
        button.setOnAction(e -> addCondition(createCondition()));
        return button;
    }

    private void addCondition (ICondition createCondition) {
        if(createCondition != null) {
            myList.add(createCondition);
        }
    }
    
    protected void add (Node node, int column, int row) {
        myGroup.add(node, column, row);
    }

    protected abstract ICondition createCondition ();

    public void show () {
        myStage.show();
    }
}
