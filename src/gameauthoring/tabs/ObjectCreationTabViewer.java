package gameauthoring.tabs;

import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.CreationControllerAttribute;
import gameauthoring.creation.forms.CreationControllerFactory;
import gameauthoring.creation.forms.CreationControllerSprite;
import gameauthoring.creation.forms.IObjectCreationView;
import gameauthoring.creation.forms.ObjectCreationView;
import gameauthoring.creation.subforms.MakeAttributeSubFormController;
import gameauthoring.creation.subforms.ISubFormControllerAttribute;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ProfileSubFormController;
import gameauthoring.creation.subforms.SelectAttributeSubFormController;
import gameauthoring.creation.subforms.SubFormControllerFactory;
import gameauthoring.creation.subforms.movement.MovementSubFormController;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import engine.definitions.GroupDefinition;
import engine.definitions.SpriteDefinition;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


/**
 * Character(Sprite) tab view class which allows users to create all weapons, enemies, defenders,
 * obstacles, and interactions. Handles selection between these sub-tabs. Serves to make creation
 * controller and subform controller using factory design.
 * 
 * @TODO: Resourcebundle for unprotectedString
 * @TODO: Interaction, Obstacle subtabs.
 * @author Jin An
 *
 */
public class ObjectCreationTabViewer implements ITabViewer {

    private TabPane myTabPane;

    private AuthorshipData myAuthorshipData;

    private List<CreationController<?>> myCCs;
    private List<IObjectCreationView<?>> myCVs;

    public ObjectCreationTabViewer () {
        initializeLists();
        init();
    }

    public ObjectCreationTabViewer (Game game) {
        myAuthorshipData = game.getAuthorshipData();
        initializeLists();
        init();
    }

    private void initializeLists () {

        
        List<String> myAttributeSFCs = new ArrayList<String>(Arrays.asList("Attribute"));
        List<String> myMissileSFCs = new ArrayList<String>();
        List<String> myEnemySFCs =
                new ArrayList<String>(Arrays.asList("SelectAttribute"));
        List<String> myDefenderSFCs =
                new ArrayList<String>(Arrays.asList("SelectAttribute"));
     

        CreationControllerFactory ccFactory = new CreationControllerFactory();
        // TODO: take sfcs out of cc constructors
        CreationController<?> ccAttributes =
                ccFactory.createAttributeCreationController("Attribute", myAttributeSFCs,
                                                            myAuthorshipData);
        CreationController<?> ccMissiles =
                ccFactory.createSpriteCreationController("Missiles", myMissileSFCs,
                                                         myAuthorshipData);
        CreationController<?> ccEnemies =
                ccFactory.createSpriteCreationController("Enemies", myEnemySFCs,
                                                         myAuthorshipData);
        CreationController<?> ccDefenders =
                ccFactory.createSpriteCreationController("Defender", myDefenderSFCs,
                                                         myAuthorshipData);

        myCCs = new ArrayList<CreationController<?>>();

        myCCs.add(ccAttributes);

        myCCs.add(ccMissiles);
        myCCs.add(ccEnemies);
        myCCs.add(ccDefenders);

        ccAttributes.init(myAttributeSFCs);

        ccMissiles.init(myMissileSFCs);
        ccEnemies.init(myEnemySFCs);
        ccDefenders.init(myDefenderSFCs);

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
            Tab tab = new Tab(myCCs.get(i).getMyTitle());
            tab.setClosable(false);
            tab.setContent(myCVs.get(i).draw());
            myTabPane.getTabs().add(tab);
        }
    }

    @Override
    public Node draw () {
        return myTabPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }

    private AuthorshipData getMyAuthorshipData () {
        return myAuthorshipData;
    }

}
