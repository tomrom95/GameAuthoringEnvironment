package engine.rendering;

import engine.Drawable;
import engine.IGamePlayable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import util.Coordinate;


/**
 * Used to render the back-end components into JavaFX responsive objects for the screen
 *
 * @author RyanStPierre
 *
 */
public class Renderer implements IRenderer {

    private IGraphicFactory myFactory;
    private Pane myPane;
    private IGamePlayable myGame;

    public Renderer (IGamePlayable game, Pane pane) {
        myPane = pane;
        myFactory = new UnscaledFactory();
        myGame = game;
    }

    @Override
    public void render () {
        myPane.getChildren().clear();
        // drawBackground();
        myGame.getDrawables().forEach(d -> draw(d));

    }

    private void drawBackground () {
        add(myFactory.getVisual(myGame.getBackroundImage()));
    }

    private void draw (Drawable drawable) {
        Node node = drawable.getDrawer().getVisualRepresentation(myFactory);
        Coordinate location = drawable.getLocation();
        node.relocate(location.getX(), location.getY());
        add(node);
    }

    private void add (Node node) {
        myPane.getChildren().add(node);
    }
}
