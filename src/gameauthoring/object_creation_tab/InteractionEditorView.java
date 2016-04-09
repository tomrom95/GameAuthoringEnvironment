package gameauthoring.object_creation_tab;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;


public class InteractionEditorView extends SubTabEditorView {

    private BorderPane myLayout;
    
    public InteractionEditorView (Tab interactionTab) {
        super(interactionTab);
    }

    @Override
    public Node draw () {
        myLayout = new BorderPane();
        return myLayout;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}
