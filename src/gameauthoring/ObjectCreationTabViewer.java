package gameauthoring;

import gameauthoring.ITabViewer;
import gameauthoring.characters.CreationController;
import gameauthoring.characters.CreationControllerAttribute;
import gameauthoring.characters.CreationControllerFactory;
import gameauthoring.characters.CreationControllerSprite;
import gameauthoring.characters.IObjectCreationView;
import gameauthoring.characters.ISubFormControllerAttribute;
import gameauthoring.characters.ISubFormControllerSprite;
import gameauthoring.characters.ObjectCreationView;
import gameauthoring.characters.SubFormControllerFactory;
import gameauthoring.object_creation_tab.AttributeEditorView;
import gameauthoring.object_creation_tab.DefenderEditorView;
import gameauthoring.object_creation_tab.EnemyEditorView;
import gameauthoring.object_creation_tab.InteractionEditorView;
import gameauthoring.object_creation_tab.WeaponEditorView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


/**
 * Character(Sprite) tab view class which allows users to create all weapons, enemies, defenders,
 * and obstacles.
 * Handles selection between different SpriteEditorViews.
 * 
 * @author Jin An
 * @author Joe Lilien
 *
 */
public class ObjectCreationTabViewer implements ITabViewer {     
    
    private TabPane myTabPane;

    private List<SubFormControllerSprite> myEnemySubforms = new ArrayList<Sprite> 
    private List<CreationController<?>> mySpriteCCs = new ArrayList<CreationController<?>>(Arrays.asList(new CreationControllerSprite(null)));
    private List<IObjectCreationView<?>> myCreationViews;
    private CreationControllerFactory ccFactory;
    private SubFormControllerFactory sfcFactory;

    private void init () {
        ccFactory = new CreationControllerFactory();
        sfcFactory = new SubFormControllerFactory();

        myCreationViews = new ArrayList<IObjectCreationView<?>>();

        String[] subForms = {"profile", "movement", "attributes", "groups" };

        List<ISubFormControllerSprite> enemiesSfcs =
                sfcFactory.createSpriteSubFormControllers(subForms);
        CreationControllerSprite enemyCC = ccFactory.createSpriteCreationController(enemiesSfcs);
        myCreationViews.add(enemyCC.getMyObjectCreationView());

        List<ISubFormControllerSprite> defendersSfcs =
                sfcFactory.createSpriteSubFormControllers(subForms);
        CreationControllerSprite defenderCC =
                ccFactory.createSpriteCreationController(defendersSfcs);
        myCreationViews.add(defenderCC.getMyObjectCreationView());

        /*
        String[] attSubForms = { "attribute" };
        List<ISubFormControllerAttribute> attributeSfcs =
                sfcFactory.createAttributeSubFormControllers(subForms);
        CreationControllerAttribute attributeCC =
                ccFactory.createAttributeCreationController(attributeSfcs);
        myCreationViews.add(defenderCC.getMyObjectCreationView());
        */

    }


    public ObjectCreationTabViewer () {
        init();
        myTabPane = createAllSubTabs();
    }

    private TabPane createAllSubTabs () {
        TabPane tabpane = new TabPane();
        for (IObjectCreationView<?> creationView : myCreationViews) {
            Tab tab = new Tab();
            tab.setContent(creationView.draw());

            tabpane.getTabs().add(tab);
        }
        /*
        myInteractionView = new InteractionEditorView(createSubTab("Interactions"));
        myWeaponView = new WeaponEditorView(createSubTab("Weapons"));

        tabpane.getTabs().addAll(myAttributeView.getTab(), myDefenderView.getTab(),
                                 myEnemyView.getTab(), myInteractionView.getTab(),
                                 myWeaponView.getTab());
                                 */
        return tabpane;
    }

    @Override
    public Node draw () {
        return myTabPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}
