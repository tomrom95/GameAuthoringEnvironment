package gameplayer;

import engine.Drawable;
import engine.IGamePlayable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


/**
 * Used to render the screen
 * 
 * @author RyanStPierre
 *
 */
public class Renderer implements IRenderer {

    private IGraphicFactory myFactory;
    private Pane myPane;

    public Renderer (IGamePlayable game, Pane pane) {
        myPane = pane;
        myFactory = new GraphicFactory();
    }

    // @Override
    // public void renderDrawables (ObservableList<Drawable> drawables) {
    // drawables.forEach(d -> draw(d));
    //
    // }

    @Override
    public void render () {
        // TODO
    }

    private void draw (Drawable drawable) {
        add(drawable.getDrawer().get().getVisualRepresentation(myFactory));
    }

    private void add (Node node) {
        myPane.getChildren().add(node);

    }

    // public static void main(String[] args) {
    // ISprite sprite = new Sprite();
    // TextGraphic tg = new TextGraphic("Ryan", 20);
    // IGraphicModule module = new GraphicModule(tg);
    // sprite.getDrawer().set(module);
    // Pane pane = new Pane();
    // Renderer r = new Renderer(pane);
    // r.draw(sprite);
    // System.out.println(pane.getChildren());
    // }
}
