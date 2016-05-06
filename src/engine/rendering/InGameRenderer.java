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
 * Used to render the back-end components into JavaFX responsive objects for the screen
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

    @Override
    protected Node getNode (Drawable item) {
        Node node = item.getDrawer().getVisualRepresentation(myFactory);
        node.setOnMouseClicked(e -> mySpriteDisplay.populate(item));
        return node;
    }

}
