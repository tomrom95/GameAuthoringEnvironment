package gameplayer;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class GamePlayerTools {

    MenuBar myBar = new MenuBar();
    IGameEngine myEngine;
    
    public GamePlayerTools (IGameEngine engine) {
        init();
    }

    private void init () {
        Menu controls = new Menu("Controls");
        myBar.getMenus().add(controls);
        MenuItem pause = new MenuItem("Pause");
        MenuItem play = new MenuItem("Play");

        pause.setOnAction(e -> myEngine.pause());
        play.setOnAction(e -> myEngine.play());
        controls.getItems().add(play);
        controls.getItems().add(pause);
       
        
    }

    public Node draw () {
        return myBar;
    }
    
}
