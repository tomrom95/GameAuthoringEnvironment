package gameauthoring.characters;

import gameauthoring.SpriteListView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


/**
 * Object that holds and displays both view items that make up object creation interface
 * 
 * @author JoeLilien
 *
 */
public class ObjectCreationView implements IObjectCreationView {

    private GridPane myCreationPane = new GridPane();

    // Should add both listCellView and formview in constructor argument in
    public ObjectCreationView (FormView form) {
        //Temporary placeholder pane
        Pane pane = new Pane();
        pane.setPrefWidth(600);
        myCreationPane.add(pane, 0, 0);
        myCreationPane.add(form.draw(), 0, 1);
    }

    @Override
    public Node draw () {
        return myCreationPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public SpriteListView getSpriteListView () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CreationController<?> getCreationController () {
        // TODO Auto-generated method stub
        return null;
    }

}
