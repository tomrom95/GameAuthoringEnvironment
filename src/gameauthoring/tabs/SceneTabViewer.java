package gameauthoring.tabs;

import engine.profile.Profile;
import java.util.List;
import java.util.Optional;
import engine.Game;
import engine.IConditionManager;
import engine.IGame;
import engine.ILevel;
import engine.ILevelManager;
import engine.Level;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.levels.LevelEditorView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.util.BasicUIFactory;
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
    private IGame myGame;
    private BasicUIFactory myUIFactory = new BasicUIFactory();

    public SceneTabViewer () {
        init();
    }

    public SceneTabViewer (IGame iGame) {
        myLevelManager = iGame.getLevelManager();
        myConditionManager = iGame.getConditionManager();

        mySprites = iGame.getAuthorshipData().getMyCreatedSprites();
        myGame = iGame;
        init();
    }

    @Override
    public void init () {
        LevelEditorView view =
                new LevelEditorView(myGame, myGame.getLevelManager().getCurrentLevel());

        myLevelTabs = new TabPane();
        myLevelTabs.getStyleClass().add("subTab");
        Tab createLevelTab = createButtonTab();
        myLevelTabs.getTabs().addAll(createLevelTab);
        addNewLevel("Start");
    }

    @Override
    public Node draw () {
        return myLevelTabs;
    }

    /**
     * Create Add level button tab. This makes the UI design clean and by disabling the tab and only
     * enabling the button, there won't be any awkward UI errors.
     * 
     * @return
     */
    private Tab createButtonTab () {
        Tab createLevelTab = new Tab();
        Button addNewLevelButton = new Button("+");
        addNewLevelButton.setOnAction(e -> addNewNamedLevel());
        createLevelTab.setGraphic(addNewLevelButton);
        createLevelTab.setDisable(true);
        createLevelTab.setClosable(false);
        return createLevelTab;
    }

    private void addNewNamedLevel () {
        Optional<String> name = new BasicUIFactory().getTextDialog("Enter", "Level Adder", "Level Name: ");
        if (name.isPresent()) {
            addNewLevel(name.get());
        }
    }

    private void addNewLevel (String name) {
        ILevel newLevel = new Level();
        newLevel.setProfile(new Profile(name));
        myLevelManager.createNewLevel(newLevel);
        LevelEditorView view =
                new LevelEditorView(myGame, myGame.getLevelManager().getCurrentLevel());
        Tab newLevelTab =
                myUIFactory.createTabText(name, true, view.draw());
        myLevelTabs.getTabs().add(newLevelTab);
        myLevelTabs.getSelectionModel().select(newLevelTab);
    }
}
