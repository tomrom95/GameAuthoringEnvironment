package gameplayer;

import engine.IGame;
import gameauthoring.Glyph;
import javafx.scene.Node;

public class SideBarDisplay implements Glyph {
    
    private IGame myGame;

    public SideBarDisplay (IGame game){
        myGame = game;
    }
    
    @Override
    public Node draw () {
        
        return null;
    }

    @Override
    public void update () { 
    }

}
