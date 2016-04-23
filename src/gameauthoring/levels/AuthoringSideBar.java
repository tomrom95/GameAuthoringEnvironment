package gameauthoring.levels;

import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.definitions.concrete.SpawnerDefinition;
import engine.rendering.LevelRenderer;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.sprites.DragCheckSpriteCell;
import gameauthoring.shareddata.DefinitionCollection;
import gameplayer.SideBarDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class AuthoringSideBar extends SideBarDisplay {

    public AuthoringSideBar (IGame game, LevelRenderer renderer) {
        super(game, renderer);
    }

    protected void fillAccordion (Accordion accordion) {
        getGame().getAuthorshipData().getMyCreatedSprites().stream().forEach(c -> {
            TitledPane toAdd = createAccordionPane(c);
            accordion.getPanes().add(toAdd);
            accordion.expandedPaneProperty().set(toAdd);
        });
        accordion.getPanes().add(createSpawnerPane());
    }

    protected TitledPane createAccordionPane (DefinitionCollection<SpriteDefinition> collection) {
        ListView<SpriteDefinition> spriteList = createSpriteList(collection.getItems());
        TitledPane pane = new TitledPane(collection.getTitle(), spriteList);
        return pane;
    }

    protected ProfileCellView<SpriteDefinition> getSpriteCellView () {
        return new DragCheckSpriteCell(getLevelView(), getController());
    }

    private TitledPane createSpawnerPane () {
        return new TitledPane("Spawners", createSpawnerList());
    }

    private Node createSpawnerList () {
        ObservableList<SpriteDefinition> list = FXCollections.observableArrayList();
        list.add(new SpawnerDefinition(getGame()));
        return createSpriteList(list);
    }

}
