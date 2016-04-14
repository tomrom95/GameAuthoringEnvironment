package gameplayer;

import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.util.Glyph;
import gameauthoring.levels.SceneController;
import gameauthoring.levels.sprites.DraggableSpriteCell;
import gameauthoring.shareddata.DefinitionCollection;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;


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
        return createAccordion ();
    }

    protected Accordion createAccordion () {
        Accordion selector = new Accordion();
        myGame.getAuthorshipData().getMyCreatedSprites().stream().forEach(c -> {
            selector.getPanes().add(createAccordionPane(c));
        });
        return selector;
    }

    private TitledPane createAccordionPane (DefinitionCollection<SpriteDefinition> collection) {
        ListView<SpriteDefinition> spriteList = createSpriteList(collection.getItems());
        TitledPane pane = new TitledPane(collection.getTitle(), spriteList);
        return pane;
    }

    protected ListView<SpriteDefinition> createSpriteList (ObservableList<SpriteDefinition> collection) {
        
        ListView<SpriteDefinition> list = new ListView<SpriteDefinition>();
        list.setItems(collection);
        list.setCellFactory(c -> new DraggableSpriteCell(levelView, myController));
        return list;
    }
}
