package gameauthoring.listdisplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.conditions.ICondition;
import engine.profile.IProfilable;
import engine.profile.Profile;
import gameauthoring.creation.cellviews.NameCellView;
import gameauthoring.util.UIFactory;
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


public abstract class SubConditionView {

    protected static final double CUSHION = 10;
    private static final String DEFAULT_IMAGE = "/images/C.png";
    ResourceBundle myLabels = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);

    private GridPane myGroup = new GridPane();
    private ObservableList<ICondition> myList;
    private TextField myName = new TextField();
    private TextField myDescription = new TextField();
    private List<Node> myNodes;
    
    public SubConditionView (ObservableList<ICondition> conditionList) {
        myList = conditionList;
        myNodes = new ArrayList<>();
    }

    protected void initStage () {
        setSizes();
        myGroup.setHgap(CUSHION);
        myGroup.setVgap(CUSHION);
        add(addProfileInfo(), 0, 0);
        initializeDisplay();

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
        vbox.getChildren().addAll(node1, node2);
        return vbox;
    }
    
    protected void addStringComboBox (ComboBox<String> combo) {
        myNodes.add(combo);      
    }

    protected void initializeDisplay () {
        initBoxes();
        add(getHBox(), 0, 1);
        myGroup.add(createButton(), 0, 2);
    }

    protected abstract void initBoxes ();

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

    protected <T extends IProfilable> ComboBox<T> createComboBox (ObservableList<T> list) {
        ComboBox<T> combo = new UIFactory().createCombo(list);
        myNodes.add(combo);
        return combo;
    }

    protected ComboBox<String> createStringComboBox (ObservableList<String> list) {
        ComboBox<String> box = new ComboBox<>(list);
        myNodes.add(box);
        return box;
    }

    protected Node getHBox () {
        HBox hbox = new HBox(CUSHION);
        for (int i = 0; i < myNodes.size(); i++) {
            String label = myLabels.getString(getLabelKey(Integer.toString(i)));
            hbox.getChildren().add(getCombo(label, myNodes.get(i)));
        }
        return hbox;
    }

    private Node getCombo (String label, Node node) {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label(label), node);
        return vbox;
    }

    protected TextField createTextField () {
        TextField text = new TextField();
        myNodes.add(text);
        return text;
    }
   
    protected abstract String getLabelKey (String key);
}
