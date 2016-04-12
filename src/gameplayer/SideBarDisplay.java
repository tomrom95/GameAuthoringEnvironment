package gameplayer;

import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.Glyph;
import gameauthoring.levels.SceneController;
import gameauthoring.levels.sprites.DraggableSpriteCell;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


/**
 * Creates side bar display of sprite that can be added to the screen.
 * Currently very similar to the authoring version, but will be changed when
 * costs are incorporated.
 * @author Tommy
 *
 */
public class SideBarDisplay implements Glyph {
    
    private IGame myGame;
    private LevelRenderer levelView;
    private SceneController myController;

    public SideBarDisplay (IGame game, LevelRenderer renderer){
        myGame = game;
        myController = new SceneController(game.getLevelManager().getCurrentLevel());
    }
    
    @Override
    public Node draw () {
        ListView<SpriteDefinition> list = new ListView<SpriteDefinition>();
        list.setItems(myGame.getAuthorshipData().getCreatedSprites());
        list.setCellFactory(new Callback<ListView<SpriteDefinition>, ListCell<SpriteDefinition>>() {
            @Override 
            public ListCell<SpriteDefinition> call(ListView<SpriteDefinition> list) {
                return new DraggableSpriteCell(levelView, myController);
            }
        });
        return list;
    }

    @Override
    public void update () { 
    }

}
