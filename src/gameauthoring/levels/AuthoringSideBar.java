package gameauthoring.levels;

import engine.IGame;
import engine.ILevel;
import engine.definitions.concrete.SpriteDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.sprites.DragCheckSpriteCell;
import gameplayer.SideBarDisplay;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import util.ScaleRatio;

public class AuthoringSideBar extends SideBarDisplay {
    
    private IGame myGame;

    public AuthoringSideBar (IGame game, ILevel level, LevelRenderer renderer, ScaleRatio scale) {
        super(level, renderer, scale);
        myGame = game;
    }

    protected void fillAccordion (Accordion accordion) {
        myGame.getAuthorshipData().getMyCreatedSpritesMap().values().stream().forEach(c -> {
            TitledPane toAdd = createAccordionPane(c);
            accordion.getPanes().add(toAdd);
            accordion.expandedPaneProperty().set(toAdd);
        });
    }

    protected ProfileCellView<SpriteDefinition> getSpriteCellView () {
        return new DragCheckSpriteCell(getLevelView(), getController());
    }

    @Override
    protected SceneController createController (ILevel level, ScaleRatio ratio) {
        return new SceneController(level, ratio);
    }

    
}
