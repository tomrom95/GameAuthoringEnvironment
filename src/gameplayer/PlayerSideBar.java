package gameplayer;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.sprites.DraggableSpriteCell;
import javafx.collections.ObservableList;
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
public class PlayerSideBar extends SideBarDisplay {
    private static final String PANE_TITLE = "Towers";

    public PlayerSideBar (IGame game, LevelRenderer renderer) {
        super(game, renderer);
    }
    
    @Override
    protected void fillAccordion (Accordion accordion) {
        accordion.getPanes().add(createAccordionPane(this.getGame().getLevelManager().getCurrentLevel().getAddableSprites()));
    }

    protected TitledPane createAccordionPane (ObservableList<SpriteDefinition> spriteList) {
        ListView<SpriteDefinition> listView = createSpriteList(spriteList);
        TitledPane pane = new TitledPane(PANE_TITLE, listView);
        return pane;
    }

    protected ProfileCellView<SpriteDefinition> getSpriteCellView () {
        return new DraggableSpriteCell(getLevelView(), getController());
    }
}
