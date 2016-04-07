package gameauthoring;

import java.util.List;
import javafx.scene.Node;


/**
 * View class for individual object list in Object Creation Tab.
 * Has "Show and Edit" button for the user to load specific ListCell on the right
 * Has "Create" button to create a FormView and load it on the right
 * 
 * Has SpriteEditorController
 * Has buttons that map cells to CreationController.showAndEdit()
 * Create button (e -> stc.createBlankSprite())
 * 
 * @author Jin An, Jeremy Schreck
 *
 */
public class ObjectListView implements IObjectListView {

    private List<ListCellView> myListCellViews;

    public ObjectListView () {

    }

    private List<ListCellView> getListCellViews () {
        return myListCellViews;
    }

    @Override
    public Node draw () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

}
