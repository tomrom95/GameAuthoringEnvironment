package gameauthoring;

import engine.definitions.SpriteDefinition;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import util.RGBColor;
import java.util.List;
import engine.ConditionManager;
import engine.Game;
import engine.IConditionManager;
import engine.ILevel;
import engine.ILevelManager;
import engine.sprite.ISprite;
import engine.sprite.SpriteType;
import engine.Level;
import engine.LevelManager;
import engine.sprite.Sprite;
import gameauthoring.levels.LevelEditorView;
import gameauthoring.tabs.ITabViewer;
import graphics.Block;
import graphics.IGraphic;
import graphics.ImageGraphic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * Level tab view class which allows the user to put together all the sprites in different levels
 * Handles selection for different level editor views.
 * 
 * @author Jin An
 *
 */
public class SceneTabViewer implements ITabViewer {

    private TabPane myLevelTabs;
    private ILevelManager myLevelManager;
    private IConditionManager myConditionManager;
    private List<DefinitionCollection<SpriteDefinition>> mySprites;

    public SceneTabViewer () {
        init();
    }

    public SceneTabViewer (Game game) {
        myLevelManager = game.getLevelManager();
        myConditionManager = game.getConditionManager();
        mySprites = game.getAuthorshipData().getMyCreatedSprites();
    }

    private void init () {
        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(new Level());
        myLevelManager = new LevelManager(startingLevel.get());
        myConditionManager = new ConditionManager();
        Game game = new Game(myLevelManager, null, myConditionManager);
        
        makeSomeSprites(game);
        LevelEditorView view = new LevelEditorView(game, startingLevel.get());

        myLevelTabs = new TabPane();
        Tab createLevelTab = createButtonTab();
        Tab firstLevelTab = new Tab("Level 1");
        myLevelTabs.getSelectionModel().select(firstLevelTab);
        firstLevelTab.setContent(view.draw());
        myLevelTabs.getTabs().addAll(createLevelTab, firstLevelTab);
    }

    @Override
    public Node draw () {
        return myLevelTabs;
    }

    private Tab createButtonTab () {
        Tab createLevelTab = new Tab();
        Button addNewLevelButton = new Button("+");
        addNewLevelButton.setOnAction(e -> addNewLevel());
        createLevelTab.setGraphic(addNewLevelButton);
        createLevelTab.setClosable(false);
        return createLevelTab;
    }

    private void addNewLevel () {
        Tab newLevelTab = new Tab("Level" + (myLevelTabs.getTabs().size()));
        myLevelTabs.getTabs().add(newLevelTab);
        myLevelTabs.getSelectionModel().select(newLevelTab);
        ObjectProperty<ILevel> newLevel = new SimpleObjectProperty<>(new Level());
        myLevelManager.createNewLevel(newLevel.get());

        Game game = new Game(myLevelManager, null, myConditionManager);
        makeSomeSprites(game);
        LevelEditorView view = new LevelEditorView(game, newLevel.get());
        Group nextLevel = new Group(view.draw());
        newLevelTab.setContent(nextLevel);
    }

    private void makeSomeSprites (Game game) {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                game.getAuthorshipData().getCreatedSprites().add(createFirstSprite());
            }
            else {
                game.getAuthorshipData().getCreatedSprites().add(createSecondSprite());
            }
        }
    }

    private SpriteDefinition createFirstSprite () {
        SpriteDefinition sprite = new SpriteDefinition();

        IGraphic graphic = new ImageGraphic(30, 30,"images/photo.png");
        sprite.setGraphic(graphic);
        sprite.setType("Person");
        sprite.setDescription("This is a person");
        return sprite;
    }

    private SpriteDefinition createSecondSprite () {
        SpriteDefinition sprite = new SpriteDefinition();
        sprite.setType("Block");
        IGraphic graphic = new Block(40, 40, RGBColor.BLACK);
        sprite.setGraphic(graphic);
        sprite.setDescription("This is a block");
        return sprite;
    }
    
    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}
