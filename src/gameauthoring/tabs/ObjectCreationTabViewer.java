package gameauthoring.tabs;

import gameauthoring.creation.factories.CreationControllerFactory;
import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.IObjectCreationView;
import gameauthoring.util.UIFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private UIFactory myUIFactory = new UIFactory();

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

        List<String> myGlobalSFCs = new ArrayList<String>(Arrays.asList("ProfileSFC", "LevelSpecific", "Attribute"));

        List<String> myAttributeSFCs = new ArrayList<String>(Arrays.asList("ProfileSFC", "Attribute"));

        List<String> myMissileSFCs = new ArrayList<String>(Arrays.asList("ProfileSFC", "Movement"));
        List<String> myEnemySFCs =
                new ArrayList<String>(Arrays.asList("ProfileSFC", "SelectAttribute", "Movement"));
        List<String> myDefenderSFCs =
                new ArrayList<String>(Arrays.asList("ProfileSFC", "SelectAttribute", "Upgrade", "Movement", "Firing"));
        List<String> myGroupSFCs = new ArrayList<>(Arrays.asList("ProfileSFC", "SelectSprite"));

        List<String> myEventSFCs = new ArrayList<String>(Arrays.asList("Events"));

        CreationControllerFactory ccFactory = new CreationControllerFactory();
        // TODO: take sfcs out of cc constructors

        
        CreationController<?> ccGlobal =
                ccFactory.createCreationController("gameauthoring.creation.forms.CreationControllerGlobals", "Global Resources", myGlobalSFCs,
                                                            myGame);

        CreationController<?> ccAttributes =
                ccFactory.createCreationController("gameauthoring.creation.forms.CreationControllerAttribute", "Character Attributes", myAttributeSFCs,
                                                            myGame);
        CreationController<?> ccMissiles =
                ccFactory.createCreationController("gameauthoring.creation.forms.CreationControllerMissile", "Missiles", myMissileSFCs,
                                                         myGame);
        CreationController<?> ccEnemies =
                ccFactory.createCreationController("gameauthoring.creation.forms.CreationControllerSprite", "Enemies", myEnemySFCs,
                                                         myGame);
        CreationController<?> ccDefenders =
                ccFactory.createCreationController("gameauthoring.creation.forms.CreationControllerSprite", "Defender", myDefenderSFCs,
                                                         myGame);
        CreationController<?> ccGroups =
                ccFactory.createCreationController("gameauthoring.creation.forms.CreationControllerGroup", "Groups", myGroupSFCs, myGame);

        CreationController<?> ccEvents =
                ccFactory.createCreationController("gameauthoring.creation.forms.CreationControllerEvent", "Events", myEventSFCs, myGame);
        CreationController<?> ccBadArgsTest =
                ccFactory.createCreationController("gameauthoring.creation.forms.FormView", "Events", myEventSFCs, myGame);
        CreationController<?> ccClassCastExceptionTest =
                ccFactory.createCreationController("gameauthoring.creation.forms.FormView");
       /*
        CreationController<?> ccGlobal =
                ccFactory.createGlobalsCreationController("Global Resources", myGlobalSFCs,
                                                            myGame);

        CreationController<?> ccAttributes =
                ccFactory.createAttributeCreationController("Character Attributes", myAttributeSFCs,
                                                            myGame);
        CreationController<?> ccMissiles =
                ccFactory.createSpriteCreationController("Missiles", myMissileSFCs,
                                                         myGame);
        CreationController<?> ccEnemies =
                ccFactory.createSpriteCreationController("Enemies", myEnemySFCs,
                                                         myGame);
        CreationController<?> ccDefenders =
                ccFactory.createSpriteCreationController("Defender", myDefenderSFCs,
                                                         myGame);
        CreationController<?> ccGroups =
                ccFactory.createGroupCC("Groups", myGroupSFCs, myGame);

        CreationController<?> ccEvents =
                ccFactory.createEventCreationController("Events", myEventSFCs, myGame);
*/

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
            Tab tab = myUIFactory.createTab(myCCs.get(i).getMyTitle(), false, myCVs.get(i).draw());
            myTabPane.getTabs().add(tab);
        }
    }

    @Override
    public Node draw () {
        return myTabPane;
    }
}
