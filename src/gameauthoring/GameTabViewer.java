package gameauthoring;

import javafx.scene.Node;
import javafx.scene.control.Tab;


/**
 * Handles selection between differnet games
 * 
 * @author Jin An
 *
 */
public class GameTabViewer implements ITabViewer {

    private Tab myGameTab;

    public GameTabViewer (Tab gameTab) {
        myGameTab = gameTab;
    }

    public Tab getTab(){
        return myGameTab;
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
