package gameauthoring.util;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Helpful factory to create common UI elements like
 * a button, combo box, header text or otherwise
 * 
 * @author Tommy, Jin An, Joe Lilien
 *
 */
public class UIFactory {

    public Button createButton (String text, EventHandler<ActionEvent> action) {
        Button newButton = new Button(text);
        newButton.setOnAction(action);
        return newButton;
    }

    public Button createImageButton (String text,
                                     ImageView imgview,
                                     EventHandler<ActionEvent> action) {
        Button newButton = new Button(text, imgview);
        newButton.setOnAction(action);
        return newButton;
    }

    public Tab createTab (String text, boolean closable, Node content) {
        Tab newTab = new Tab();
        newTab.setText(text);
        newTab.setClosable(closable);
        newTab.setContent(content);
        return newTab;
    }

    public Image getImageFromNode (Node node) {
        return node.snapshot(new SnapshotParameters(), null);
    }
    
    public ScrollPane makeScrollPane(Node content, int width, int height){
        ScrollPane pane = new ScrollPane(content);
        pane.setPrefSize(width, height);
        return pane;
    }
    
    public HBox makeHBox(double spacing, Node...content ){
        HBox box = new HBox(spacing);
        box.getChildren().addAll(content);
        return box;
    }
    
    public VBox makeVBox(double spacing, Node...content ){
        VBox box = new VBox(spacing);
        if(content!=null){
            box.getChildren().addAll(content);
        }
        return box;
    }
}
