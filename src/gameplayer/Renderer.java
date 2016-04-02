package gameplayer;

import engine.Drawable;
import engine.IGamePlayable;
import graphics.GraphicFactory;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import util.Coordinate;


/**
 * Used to render the screen
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
        myFactory = new GraphicFactory();
        myGame = game;
    }

    @Override
    public void render () {
        myGame.getDrawables().forEach(d -> draw(d));
        
    }

    private void draw (Drawable drawable) {
        Node node = drawable.getDrawer().get().getVisualRepresentation(myFactory);
        Coordinate location = drawable.getLocation().get();
        node.relocate(location.getX(), location.getY());
        add(node);
    }

    private void add (Node node) {
        myPane.getChildren().add(node);

    }
}
