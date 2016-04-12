package gameplayer;

import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.util.Glyph;
import gameauthoring.levels.SceneController;
import gameauthoring.levels.sprites.DraggableSpriteCell;
import gameauthoring.shareddata.DefinitionCollection;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.util.Callback;


/**
 * Creates side bar display of sprite that can be added to the screen.
 * Currently very similar to the authoring version, but will be changed when
 * costs are incorporated.
 * 
 * @author Tommy
 *
 */
public class SideBarDisplay implements Glyph {

    private IGame myGame;
    private LevelRenderer levelView;
    private SceneController myController;

    public SideBarDisplay (IGame game, LevelRenderer renderer) {
        myGame = game;
        myController = new SceneController(game.getLevelManager().getCurrentLevel());
        levelView = renderer;
    }

    @Override
    public Node draw () {
        Accordion selector = new Accordion();
        myGame.getAuthorshipData().getMyCreatedSprites().stream().forEach(c -> {
            selector.getPanes().add(createAccordionPane(c));
        });
        return selector;
    }

    private TitledPane createAccordionPane (DefinitionCollection<SpriteDefinition> collection) {
        ListView<SpriteDefinition> spriteList = createSpriteList(collection);
        TitledPane pane = new TitledPane(collection.getTitle(), spriteList);
        return pane;
    }

    private ListView<SpriteDefinition> createSpriteList (DefinitionCollection<SpriteDefinition> collection) {
        
        ListView<SpriteDefinition> list = new ListView<SpriteDefinition>();
        list.setItems(collection.getItems());
        list.setCellFactory(new Callback<ListView<SpriteDefinition>, ListCell<SpriteDefinition>>() {
            @Override
            public ListCell<SpriteDefinition> call (ListView<SpriteDefinition> list) {
                return new DraggableSpriteCell(levelView, myController);
            }
        });
        return list;
    }

    @Override
    public void update () {
    }

}
