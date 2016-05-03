package gameplayer;

import engine.IGame;
import engine.ILevel;
import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.GameSceneController;
import gameauthoring.levels.SceneController;
import javafx.collections.ObservableList;
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
public class PlayerSideBar extends SideBarDisplay {
    private static final String PANE_TITLE = "Towers";

    public PlayerSideBar (IGame game, LevelRenderer renderer, ScaleRatio ratio) {
        super(game.getLevelManager().getCurrentLevel(), renderer, ratio);
    }

    @Override
    protected void fillAccordion (Accordion accordion) {
        accordion.getPanes().add(createAccordionPane(getLevel().getAddableSprites()));
    }

    protected TitledPane createAccordionPane (ObservableList<SpriteDefinition> spriteList) {
        ListView<SpriteDefinition> listView = createSpriteList(spriteList);
        TitledPane pane = new TitledPane(PANE_TITLE, listView);
        return pane;
    }

    @Override
    protected ProfileCellView<SpriteDefinition> getSpriteCellView () {
        return new PlayerSideBarCell(getLevelView(), getController());
    }

    @Override
    protected SceneController createController (ILevel myLevel, ScaleRatio ratio) {
        return new GameSceneController(getLevel(), ratio);
    }
}
