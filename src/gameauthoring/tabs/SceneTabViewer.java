package gameauthoring.tabs;

import engine.definitions.SpriteDefinition;
import java.util.List;
import engine.Game;
import engine.IConditionManager;
import engine.ILevel;
import engine.ILevelManager;
import engine.Level;
import gameauthoring.levels.LevelEditorView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.util.UIFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * Level tab view class which allows the user to put together all the sprites in different levels
 * Handles selection for different level editor views.
 * 
 * @TODO: Use UI Factory to refactor creating buttons and tabs
 * @author Jin An
 *
 */
public class SceneTabViewer implements ITabViewer {

    private TabPane myLevelTabs;
    private ILevelManager myLevelManager;
    private IConditionManager myConditionManager;
    private List<DefinitionCollection<SpriteDefinition>> mySprites;
    private Game myGame;
    private UIFactory myUIFactory = new UIFactory();

    public SceneTabViewer () {
        init();
    }

    public SceneTabViewer (Game game) {
        myLevelManager = game.getLevelManager();
        myConditionManager = game.getConditionManager();

        mySprites = game.getAuthorshipData().getMyCreatedSprites();
        myGame = game;
        init();
    }

    @Override
    public void init () {
        LevelEditorView view =
                new LevelEditorView(myGame, myGame.getLevelManager().getCurrentLevel());

        myLevelTabs = new TabPane();
        Tab createLevelTab = createButtonTab();
        Tab firstLevelTab = myUIFactory.createTab("Level 1", false, view.draw());
        myLevelTabs.getSelectionModel().select(firstLevelTab);
        myLevelTabs.getTabs().addAll(createLevelTab, firstLevelTab);
    }

    @Override
    public Node draw () {
        return myLevelTabs;
    }

    /**
     * Create Add level button tab. This makes the UI design clean and by disabling the tab and only
     * enabling the button,  there won't be any awkward UI errors.
     * 
     * @return
     */
    private Tab createButtonTab () {
        Tab createLevelTab = new Tab();
        Button addNewLevelButton = new Button("+");
        addNewLevelButton.setOnAction(e -> addNewLevel());
        createLevelTab.setGraphic(addNewLevelButton);
        createLevelTab.setDisable(true);
        createLevelTab.setClosable(false);
        return createLevelTab;
    }

    private void addNewLevel () {
        ObjectProperty<ILevel> newLevel = new SimpleObjectProperty<>(new Level());
        myLevelManager.createNewLevel(newLevel.get());
        LevelEditorView view =
                new LevelEditorView(myGame, myGame.getLevelManager().getCurrentLevel());
        Tab newLevelTab =
                myUIFactory.createTab("Level" + (myLevelTabs.getTabs().size()), true, view.draw());
        myLevelTabs.getTabs().add(newLevelTab);
        myLevelTabs.getSelectionModel().select(newLevelTab);
    }
}
