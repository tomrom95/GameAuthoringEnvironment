package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.conditions.ICondition;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class ConditionView extends ListDisplay<ICondition> {

    private ResourceBundle myStyle = ResourceBundle.getBundle("defaults/styling_class");
    private Pane myEditor = new Pane();
    private ListView<String> myOptions;
    
    public ConditionView (ObservableList<ICondition> list) {
        super(list);
        myOptions = new ListView<>(getList());
       
    }
    
    protected ListView<String> getOptions () {
        return myOptions;
    }
    
    protected Pane getEditor () {
        myEditor.getStyleClass().add(getStyle("EditorPane"));
        return myEditor;
    }

    protected abstract ObservableList<String> getList ();
    
    public void applyToOptions (EventHandler<MouseEvent> onClick) {
        getOptions().setOnMouseClicked(onClick);
    }

    public String getSelection () {
        return getOptions().getSelectionModel().getSelectedItem();
    }

    public void populate (Node node) {
        getEditor().getChildren().clear();
        getEditor().getChildren().add(node);
    }
    
    public String getStyle (String style) {
        return myStyle.getString(style);
    }

}
