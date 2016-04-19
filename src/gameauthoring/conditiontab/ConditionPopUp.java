package gameauthoring.conditiontab;

import engine.conditions.ICondition;
import engine.profile.IProfilable;
import engine.profile.Profile;
import gameauthoring.creation.cellviews.NameCellView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public abstract class ConditionPopUp {

    protected static final double CUSHION = 10;
    private static final String DEFAULT_IMAGE = "/images/C.png";
    
    private GridPane myGroup;
    private ObservableList<ICondition> myList;
    private TextField myName = new TextField ();
    private TextField myDescription = new TextField ();
    

    public ConditionPopUp (ObservableList<ICondition> conditionList) {
        myList = conditionList;
    }

    protected void initStage () {

        setSizes();
        myGroup = new GridPane();
        myGroup.setHgap(CUSHION); 
        myGroup.setVgap(CUSHION);
        add(addProfileInfo(), 0 , 0);
        initializeDisplay();
        myGroup.add(createButton(), 0, 2);

    }
    
    private void setSizes () {
        myDescription.setPrefSize(350, 200);
        
    }

    private Node addProfileInfo () {
        HBox hbox = new HBox(CUSHION);
        hbox.getChildren().add(createVBox(new Label("Name"), myName));
        hbox.getChildren().add(createVBox(new Label("Description"), myDescription));
        
        return hbox;
    }

    private Node createVBox (Node node1, Node node2) {
        VBox vbox = new VBox(CUSHION);
        vbox.getChildren().addAll(node1,node2);
        return vbox;
    }

    protected void addCellFactory (ComboBox<? extends IProfilable> comboBox) {
        comboBox.setCellFactory(c -> new NameCellView<>());
        comboBox.setButtonCell(new NameCellView<>());
    }

    protected abstract void initializeDisplay ();

    private Node createButton () {
        Button button = new Button("Create");
        button.setOnAction(e -> addCondition(createCondition()));
        return button;
    }

    private void addCondition (ICondition createCondition) {
        if (createCondition != null) {
            myList.add(createCondition);
        }
    }

    protected void add (Node node, int column, int row) {
        myGroup.add(node, column, row);
    }

    private ICondition createCondition () {
        ICondition condition = subCreation();
        condition.setProfile(new Profile(myName.getText(), myDescription.getText(), DEFAULT_IMAGE));
        return condition;
    }
    
    protected abstract ICondition subCreation ();

    public Pane show () {
        return myGroup;
    }
}
