package engine.rendering;

import engine.IGamePlayable;
import javafx.scene.layout.Pane;


/**
 * Used to render the back-end components into JavaFX responsive objects for the screen
 * 
 * @author RyanStPierre
 *
 */
public class InGameRenderer extends LevelRenderer {

    private IGraphicFactory myFactory;
    private IGamePlayable myGame;

    public InGameRenderer (IGamePlayable game, Pane pane) {
        super(pane);
        myFactory = new UnscaledFactory();
        myGame = game;
    }


    @Override
    void drawSprites () {
        myGame.getDrawables().forEach(d -> {
            this.draw(d.getDrawer().get().getVisualRepresentation(myFactory), d);
        });
    }

    @Override
    String getBackgroundURL () {
        return myGame.getBackroundImage().getUrlProperty().get();
    }

}
