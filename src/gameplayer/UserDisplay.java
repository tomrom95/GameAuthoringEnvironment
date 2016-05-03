package gameplayer;

import engine.IGame;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


/**
 * A combination of the SpriteDisplay and HUD
 *
 * @author RyanStPierre
 *
 */

public class UserDisplay {

    private VBox myPane;
    private SpriteDisplay mySpriteDisplay;
    private HeadsUpDisplay myHUD;

    public UserDisplay (IGame myGame) {
        mySpriteDisplay = new SpriteDisplay();
        myHUD = new HeadsUpDisplay(myGame);
        initPane();

    }

    private void initPane () {
        myPane = new VBox();
        myPane.getChildren().add(myHUD.draw());
        myPane.getChildren().add(mySpriteDisplay.draw());
    }

    public Node drawSpriteDisplay () {
        return mySpriteDisplay.draw();
    }

    public SpriteDisplayController getSpriteDisplay () {
        return mySpriteDisplay.getController();
    }

    public Node draw () {

        return myPane;

    }

    public double getWidth () {
        return myPane.getWidth();
    }

}
