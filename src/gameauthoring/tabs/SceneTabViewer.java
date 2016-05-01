package gameauthoring.tabs;

import engine.profile.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.IGame;
import engine.ILevel;
import engine.ILevelManager;
import engine.Level;
import gameauthoring.levels.LevelEditorView;
import gameauthoring.util.BasicUIFactory;
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

    private static final int FIRST = 1;
    private TabPane myLevelTabs;
    private ILevelManager myLevelManager;
    private IGame myGame;
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private List<LevelEditorView> myViews;
    private ResourceBundle myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
            .getInstance().getCurrentLocaleProperty().get());

    public SceneTabViewer () {
        init();
    }

    public SceneTabViewer (IGame iGame) {
        myLevelManager = iGame.getLevelManager();
        myGame = iGame;
        init();
    }

    @Override
    public void init () {
        myViews = new ArrayList<>();
        myLevelTabs = new TabPane();
        myLevelTabs.getStyleClass().add("subTab");
        Tab createLevelTab = createButtonTab();
        myLevelTabs.getTabs().addAll(createLevelTab);
        myLevelManager.getLevels().stream().forEachOrdered(level -> {
            displayLevel(level.getProfile().getName().get(), level);
        });
    }

    private void displayLevel (String name, ILevel level) {
        LevelEditorView view =
                new LevelEditorView(myGame, level);
        myViews.add(FIRST, view);
        Tab newLevelTab =
                myUIFactory.createTabText(name, true, view.draw());
        newLevelTab.setOnClosed(e -> remove(level, view));
        myLevelTabs.getTabs().add(FIRST, newLevelTab);
        
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
        Optional<String> name =
                new BasicUIFactory().getTextDialog(myLabel.getString("Enter"),
                                                   myLabel.getString("LevelAdder"),
                                                   myLabel.getString("LevelName"));
        if (name.isPresent()) {
            addNewLevel(name.get(), new Level());
        }
    }

    private void addNewLevel (String name, ILevel newLevel) {

        newLevel.setProfile(new Profile(name));
        myLevelManager.createNewLevel(newLevel);
        LevelEditorView view =
                new LevelEditorView(myGame, newLevel);
        myViews.add(view);
        Tab newLevelTab =
                myUIFactory.createTabText(name, true, view.draw());
        newLevelTab.setOnClosed(e -> remove(newLevel, view));
        myLevelTabs.getTabs().add(newLevelTab);
        myLevelTabs.getSelectionModel().select(newLevelTab);
    }

    /**
     * Removes level and view upon tab being closed
     * 
     * @param newLevel
     * @param view
     */
    private void remove (ILevel newLevel, LevelEditorView view) {
        myLevelManager.remove(newLevel);
        myViews.remove(view);
    }

    public void rescale (double width, double height) {
        myViews.stream().forEach(view -> view.rescale(width, height));
    }

}
