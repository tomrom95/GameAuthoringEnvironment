package gameplayer;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.SceneController;
import gameauthoring.shareddata.DefinitionCollection;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import util.ScaleRatio;


/**
 * Creates side bar display of sprite that can be added to the screen.
 * Currently very similar to the authoring version, but will be changed when
 * costs are incorporated.
 * 
 * @author Tommy
 *
 */
public abstract class SideBarDisplay extends SizeableGlyph {

    private IGame myGame;
    private LevelRenderer levelView;
    private SceneController myController;
    private Accordion myAccordion;

    public SideBarDisplay (IGame game, LevelRenderer renderer, ScaleRatio ratio) {
        myGame = game;
        levelView = renderer;
        myController = createController(game, ratio);
    }

    protected abstract SceneController createController (IGame game, ScaleRatio ratio);

    @Override
    public Node draw () {
        myAccordion = createAccordion();
        fillAccordion(myAccordion);
        return myAccordion;
    }

    private Accordion createAccordion () {
        Accordion selector = new Accordion();
        selector.setMaxSize(parseString(getString("SideBarWidth")),
                            parseString(getString("SideBarHeight")));
        return selector;        
    }
    
    protected abstract void fillAccordion (Accordion accordion);

    protected abstract ProfileCellView<SpriteDefinition> getSpriteCellView ();
    
    protected TitledPane createAccordionPane (DefinitionCollection<SpriteDefinition> collection) {
        ListView<SpriteDefinition> spriteList = createSpriteList(collection.getItems());
        TitledPane pane = new TitledPane(collection.getTitle(), spriteList);
        return pane;
    }

    protected ListView<SpriteDefinition> createSpriteList (ObservableList<SpriteDefinition> collection) {
        ListView<SpriteDefinition> list = new ListView<SpriteDefinition>();
        list.setItems(collection);
        list.setCellFactory(c -> getSpriteCellView());
        return list;
    }
                                                                   
    protected IGame getGame () {
        return myGame;
    }
    
    protected LevelRenderer getLevelView () {
        return levelView;
    }
    
    protected SceneController getController () {
        return myController;
    }

    public double getWidth () {
        return myAccordion.getWidth();
    }
}
