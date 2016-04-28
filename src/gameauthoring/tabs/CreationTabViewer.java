package gameauthoring.tabs;

import gameauthoring.creation.factories.CreationControllerFactory;
import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.ICreationView;
import gameauthoring.util.BasicUIFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.AuthorshipData;
import engine.Game;
import engine.IGame;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * Character(Sprite) tab view class which allows users to create all weapons, enemies, defenders,
 * obstacles, and interactions. Handles selection between these sub-tabs. Serves to make creation
 * controller and subform controller using factory design.
 * 
 * @TODO: Resourcebundle for unprotectedString
 * @TODO: Interaction, Obstacle subtabs.
 * @author Jin An, Joe Lilien, Jeremy Schreck
 *
 */
public class CreationTabViewer implements ITabViewer {

    private TabPane myTabPane;
    private BasicUIFactory myUIFactory = new BasicUIFactory();

    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);

    private IGame myGame;

    private List<CreationController<?>> myCCs;
    private List<ICreationView<?>> myCVs;

    public CreationTabViewer (IGame iGame) {
        myGame = iGame;
        initializeLists();
        init();
    }

    private void initializeLists () {
        CreationControllerFactory ccFactory = new CreationControllerFactory(myGame);
        myCCs = ccFactory.createCreationControllers();
    }

    @Override
    public void init () {
        myTabPane = new TabPane();
        myTabPane.getStyleClass().add("subTab");
        generateCreationViewList();
        generateAllSubTabs();
    }

    private void generateCreationViewList () {
        myCVs = new ArrayList<ICreationView<?>>();
        for (CreationController<?> cc : myCCs) {
            myCVs.add(cc.getMyCreationView());
        }
    }

    private void generateAllSubTabs () {
        for (int i = 0; i < myCCs.size(); i++) {
            Tab tab =
                    myUIFactory.createTabText(myCCs.get(i).getMyTitle(), false,
                                              myCVs.get(i).draw());
            myTabPane.getTabs().add(tab);
        }
    }

    @Override
    public Node draw () {
        return myTabPane;
    }
}
