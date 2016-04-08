package gameauthoring;

import modules.GraphicModule;
import modules.IGraphicModule;
import util.RGBColor;
import engine.ConditionManager;
import engine.Game;
import engine.ILevel;
import engine.ISprite;
import engine.Level;
import engine.LevelManager;
import engine.Sprite;
import gameauthoring.levels.LevelEditorView;
import graphics.Block;
import graphics.ImageGraphic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


/**
 * Level tab view class which allows the user to put together all the sprites in different levels
 * Handles selection for different level editor views.
 * 
 * @author Jin An
 *
 */
public class SceneTabViewer implements ITabViewer {

    private Tab mySceneTab;
    private TabPane myLevelTabs;
    private LevelManager myLevelManager;
    private ConditionManager myConditionManager;

    public SceneTabViewer (Tab sceneTab) {
        mySceneTab = sceneTab;
    }

    public Tab getTab () {
        return mySceneTab;
    }

    @Override
    public Node draw () {
        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(new Level());
        myLevelManager = new LevelManager(startingLevel);
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
        mySceneTab.setContent(myLevelTabs);
        return myLevelTabs;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
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
        myLevelManager.createNewLevel(newLevel);

        Game game = new Game(myLevelManager, null, myConditionManager);
        makeSomeSprites(game);
        LevelEditorView view = new LevelEditorView(game, newLevel.get());
        Group nextLevel = new Group(view.draw());
        newLevelTab.setContent(nextLevel);
    }

    private void makeSomeSprites (Game game) {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                game.getAuthorshipData().getCreatedSprites()
                        .add(new SimpleObjectProperty<>(createFirstSprite()));
            }
            else {
                game.getAuthorshipData().getCreatedSprites()
                        .add(new SimpleObjectProperty<>(createSecondSprite()));
            }
        }
    }

    private ISprite createFirstSprite () {
        ISprite sprite = new Sprite();
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new ImageGraphic(30, 30,
                                                                              "images/photo.png")));
        sprite.getDrawer().set(g.get());
        return sprite;
    }

    private ISprite createSecondSprite () {
        ISprite sprite = new Sprite();
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new Block(40, 40, RGBColor.BLACK)));
        sprite.getDrawer().set(g.get());
        return sprite;
    }
}
