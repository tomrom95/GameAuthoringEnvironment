package gameauthoring.levels;

import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.definitions.concrete.SpawnerDefinition;
import engine.rendering.LevelRenderer;
import gameplayer.SideBarDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class AuthoringSideBar extends SideBarDisplay {
    
    private IGame myGame;

    public AuthoringSideBar (IGame game, LevelRenderer renderer) {
        super(game, renderer);
        myGame = game;
    }
    
    @Override
    public Accordion createAccordion () {
        Accordion accordion = super.createAccordion();
        accordion.getPanes().add(createSpawnerPane());
        return accordion;
    }

    private TitledPane createSpawnerPane () {
        return new TitledPane("Spawners", createSpawnerList());
    }

    private Node createSpawnerList () {
        ObservableList<SpriteDefinition> list = FXCollections.observableArrayList();
        list.add(new SpawnerDefinition(myGame));
        return createSpriteList(list);
    }

}
