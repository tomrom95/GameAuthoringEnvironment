package gameauthoring;

import engine.ICondition;
import gameauthoring.object_creation_tab.SubTabEditorView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ListDisplay<T> extends SubTabEditorView {

    private ListView<T> myListView;
    private BorderPane myPane;
    private ComboBox<String> myBox;
    private Button myButton;
    private ObservableList<T> myList;
    
    public ListDisplay (Tab tab, ObservableList<T> conditionList, ObservableList<String> myOptions) {
        super(tab);
        myList = conditionList;
        myPane = new BorderPane();
        myListView = new ListView<>(conditionList);
        myButton = new Button ("+");
        initBox(myOptions);
        initPane();
        
    }

    public ObservableList<T> getList () {
       return myList;
    }
    private void initBox (ObservableList<String> myOptions) {
        myBox = new ComboBox<>(myOptions);       
    }

    private void initPane () {
        myPane = new BorderPane();
        myPane.topProperty().set(createTop());
        myPane.centerProperty().set(myListView);

    }
    
    private Node createTop () {
        HBox hbox = new HBox ();
        hbox.getChildren().add(myBox);
        hbox.getChildren().add(myButton);
        return hbox;
    }

    public ComboBox<String> getBox () {
        return myBox;
    }
    
    public Button getButton () {
        return myButton;
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
