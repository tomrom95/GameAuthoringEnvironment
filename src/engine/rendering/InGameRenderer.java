package engine.rendering;

import java.util.Collection;
import engine.Drawable;
import engine.IGamePlayable;
import gameplayer.SpriteDisplayController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import util.ScaleRatio;


/**
 * Used to render the back-end components into JavaFX responsive objects for the screen
 *
 * @author RyanStPierre
 *
 */
public class InGameRenderer extends LevelRenderer<Drawable> {

    private IGraphicFactory myFactory;
    private IGamePlayable myGame;
    private SpriteDisplayController mySpriteDisplay;
    private boolean myFirstTime;

    public InGameRenderer (IGamePlayable game,
                           Pane pane,
                           SpriteDisplayController spriteDisplay,
                           ScaleRatio scale) {
        super(pane, scale);
        myFactory = new UnscaledFactory();
        myGame = game;
        myFirstTime = true;
        mySpriteDisplay = spriteDisplay;
    }

    @Override
    public void render () {
        // TODO possibly use invalidation listener in case the background image changes, such as in
        // the case of a level change
        if (myGame.getSwitched()) {
            myFirstTime = true;
        }
        if (myFirstTime) {
            drawBackground(getBackgroundURL());
            myFirstTime = false;
        }
        drawSprites();
    }

    @Override
    protected String getBackgroundURL () {
        return myGame.getBackroundImage().getUrlProperty().get();
    }

    @Override
    protected double scaledHeight () {
        return getScale().scale(myGame.getLevelBounds().getHeight());
    }

    @Override
    protected double scaledWidth () {
        return getScale().scale(myGame.getLevelBounds().getWidth());
    }

    @Override
    protected Collection<? extends Drawable> getList () {
        return myGame.getDrawables();
    }

    @Override
    protected Node getNode (Drawable item) {
        Node node = item.getDrawer().getVisualRepresentation(myFactory);
        node.setOnMouseClicked(e -> mySpriteDisplay.populate(item));
        return node;
    }

}
