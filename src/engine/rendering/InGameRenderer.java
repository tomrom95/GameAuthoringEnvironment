// This entire file is part of my masterpiece
// Ryan St.Pierre
/**
 * I did make minor changes to this class (besides moving methods to the parent class)
 * The InGameRenderer is updated every update cycle. Thus, for performance reasons it cannot render
 * the background every time render is called. For this reason this class needs to override render.
 * The class does a check to see if the background should be rendered, if so the parent class'
 * render is called, if not the render just draws the Sprites. Previously this class was storing a
 * boolean flag called "firstTime" that was initially set to true. Every time render was called this
 * boolean value would be checked in order to see if the background should be rendered. If so, the
 * flag would be set to false. This boolean flag was a cheap and sloppy way of getting the
 * background to render once. In addition, when the window was resized the GamePlayer class had to
 * tell this class to reset that boolean flag to true.
 * 
 * This boolean flag has been swapped for a query and change listener. This class already had access
 * to the ScaleRatio (which signals the window resizing). By adding a ChangeListener no external
 * classes have to tell this class to reset its boolean flag. In other words the logic of when the
 * background should be redrawn is managed internally. Clearly this is better encapsulation and
 * makes the code easier to follow/read. In addition, the renderer now asks the playable game if
 * levels have been swapped.
 * 
 * Note: to accomplish the change listener the internal working of the ScaleRatio class was changed
 * to include a DoubleProperty rather than a double. I have not committed this class with my
 * masterpiece for the sake of brevity
 * 
 * This class does not try to do too much.  It uses the help of a UI factory (UnscaledFactory()) to 
 * create the Node representations of Drawables.  Using different JavaFx structures for the given
 * graphics held by the Drawable could easily by changed by using a different factory.  This keeps
 * choice of JavaFX structures independent of this class (keeping it closed).
 */
package engine.rendering;

import java.util.Collection;
import engine.Drawable;
import engine.IGamePlayable;
import gameplayer.SpriteDisplayController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import util.ScaleRatio;


/**
 * Visually renders the currently running level in the game
 *
 * @author RyanStPierre
 *
 */
public class InGameRenderer extends LevelRenderer<Drawable> {

    private IGraphicFactory myFactory = new UnscaledFactory();
    private IGamePlayable myGame;
    private SpriteDisplayController mySpriteDisplay;

    public InGameRenderer (IGamePlayable game,
                           Pane pane,
                           SpriteDisplayController spriteDisplay,
                           ScaleRatio scale) {
        super(pane, scale);
        myGame = game;
        mySpriteDisplay = spriteDisplay;
        addListener();
    }

    /**
     * For performance reasons the background image cannot be rendered every update cycle
     * This listener renders the background when the scale ratio changes,
     * to allow the background to fit the new window specifications
     */
    private void addListener () {
        getScale().addListener(new ChangeListener<Number>() {
            @Override
            public void changed (ObservableValue<? extends Number> observedVal,
                                 Number oldVal,
                                 Number newVal) {
                drawBackground();
            }
        });
    }

    /**
     * Only renders the background when the image is new
     * myGame.getLevelSwitch indicates to the renderer that the background needs to be rendered
     */
    @Override
    public void render () {
        if (myGame.getLevelSwitch()) {
            super.render();
        }
        else {
            drawSprites();
        }
    }

    @Override
    protected String getBackgroundURL () {
        return myGame.getBackroundImage().getUrlProperty().get();
    }

    @Override
    protected double boundHeight () {
        return myGame.getLevelBounds().getHeight();
    }

    @Override
    protected double boundWidth () {
        return myGame.getLevelBounds().getWidth();
    }

    @Override
    protected Collection<? extends Drawable> getList () {
        return myGame.getDrawables();
    }

    /**
     * Upon clicking the SpriteDisplay will be populated
     */
    @Override
    protected Node getNode (Drawable drawable) {
        Node node = drawable.getDrawer().getVisualRepresentation(myFactory);
        node.setOnMouseClicked(e -> mySpriteDisplay.populate(drawable));
        return node;
    }

}
