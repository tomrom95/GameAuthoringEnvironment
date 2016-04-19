package gameplayer;

import engine.IGame;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


public class UserDisplay {

    private SpriteDisplay mySpriteDisplay;
    private HeadsUpDisplay myHUD;
    
    public UserDisplay (IGame myGame) {
        mySpriteDisplay = new SpriteDisplay();
        myHUD = new HeadsUpDisplay(myGame);

    }

    public Node drawSpriteDisplay () {
        return mySpriteDisplay.draw();
    }

    public SpriteDisplay getSpriteDisplay () {
        return mySpriteDisplay;
    }

    public Node draw () {

        VBox vbox = new VBox();
        vbox.getChildren().add(myHUD.draw());
        vbox.getChildren().add(mySpriteDisplay.draw());
        return vbox;

    }

}
