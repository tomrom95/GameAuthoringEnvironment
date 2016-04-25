package gameauthoring.tabs;

import gameauthoring.creation.factories.CreationControllerFactory;
import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.IObjectCreationView;
import gameauthoring.util.BasicUIFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
public class ObjectCreationTabViewer implements ITabViewer {

    private TabPane myTabPane;
    private BasicUIFactory myUIFactory = new BasicUIFactory();
    private ResourceBundle myControllerResources = ResourceBundle
            .getBundle("defaults/create_creation_controller");
    private ResourceBundle myLang = ResourceBundle.getBundle("languages/labels", Locale.ENGLISH);

    private IGame myGame;

    private List<CreationController<?>> myCCs;
    private List<IObjectCreationView<?>> myCVs;

    public ObjectCreationTabViewer () {
        initializeLists();
        init();
    }

    public ObjectCreationTabViewer (IGame iGame) {
        myGame = iGame;
        initializeLists();
        init();
    }

    private void initializeLists () {

        List<String> myGlobalSFCs =
                new ArrayList<String>(Arrays.asList("ProfileSFC", "LevelSpecific", "Attribute"));

        List<String> myAttributeSFCs =
                new ArrayList<String>(Arrays.asList("ProfileSFC", "Attribute"));

        List<String> myMissileSFCs = new ArrayList<String>(Arrays.asList("ProfileSFC", "Movement"));
        List<String> myEnemySFCs =
                new ArrayList<String>(Arrays.asList("ProfileSFC", "SelectAttribute", "Movement"));
        List<String> myDefenderSFCs =

                new ArrayList<String>(Arrays.asList("ProfileSFC", "SelectAttribute", "Upgrade",
                                                    "Cost", "Movement", "FireMult"));
        List<String> myGroupSFCs = new ArrayList<>(Arrays.asList("ProfileSFC", "SelectSprite"));

        List<String> myEventSFCs = new ArrayList<String>(Arrays.asList("Events"));

        CreationControllerFactory ccFactory = new CreationControllerFactory();
        // TODO: take sfcs out of cc constructors

        CreationController<?> ccGlobal =
                ccFactory.createCreationController(myControllerResources.getString("Globals"),
                                                   myLang.getString("Globals"), myGlobalSFCs,
                                                   myGame);

        CreationController<?> ccAttributes =
                ccFactory.createCreationController(myControllerResources.getString("Attributes"),
                                                   myLang.getString("Attributes"), myAttributeSFCs,
                                                   myGame);
        CreationController<?> ccMissiles =
                ccFactory.createCreationController(myControllerResources.getString("Missiles"),
                                                   myLang.getString("Missiles"), myMissileSFCs,
                                                   myGame);
        CreationController<?> ccEnemies =
                ccFactory.createCreationController(myControllerResources.getString("Enemies"),
                                                   myLang.getString("Enemies"), myEnemySFCs,
                                                   myGame);
        CreationController<?> ccDefenders =
                ccFactory.createCreationController(myControllerResources.getString("Defenders"),
                                                   myLang.getString("Defenders"), myDefenderSFCs,
                                                   myGame);
        CreationController<?> ccGroups =
                ccFactory.createCreationController(myControllerResources.getString("Groups"),
                                                   myLang.getString("Groups"), myGroupSFCs, myGame);
        CreationController<?> ccEvents =
                ccFactory.createCreationController(myControllerResources.getString("Events"),
                                                   myLang.getString("Events"), myEventSFCs, myGame);
     

        myCCs = new ArrayList<CreationController<?>>();

        myCCs.add(ccGlobal);
        myCCs.add(ccAttributes);
        myCCs.add(ccMissiles);

        myCCs.add(ccEnemies);
        myCCs.add(ccDefenders);

        myCCs.add(ccGroups);

        myCCs.add(ccEvents);

        ccGlobal.init(myGlobalSFCs);
        ccAttributes.init(myAttributeSFCs);
        ccMissiles.init(myMissileSFCs);

        ccEnemies.init(myEnemySFCs);
        ccDefenders.init(myDefenderSFCs);
        ccGroups.init(myGroupSFCs);

        ccEvents.init(myEventSFCs);

    }

    @Override
    public void init () {
        myTabPane = new TabPane();
        myTabPane.getStyleClass().add("subTab");
        generateCreationViewList();
        generateAllSubTabs();
    }

    private void generateCreationViewList () {
        myCVs = new ArrayList<IObjectCreationView<?>>();
        for (CreationController<?> cc : myCCs) {
            myCVs.add(cc.getMyObjectCreationView());
        }
    }

    private void generateAllSubTabs () {
        for (int i = 0; i < myCCs.size(); i++) {
            Tab tab = myUIFactory.createTabText(myCCs.get(i).getMyTitle(), false, myCVs.get(i).draw());
            myTabPane.getTabs().add(tab);
        }
    }

    @Override
    public Node draw () {
        return myTabPane;
    }
}
