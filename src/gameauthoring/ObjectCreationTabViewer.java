package gameauthoring;

import gameauthoring.ITabViewer;
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
 *
 */
public class ObjectCreationTabViewer implements ITabViewer {

    private TabPane myTabPane;

    private List<CreationControllerSprite> mySpriteCCs;
    private List<IObjectCreationView<?>> myCreationViews;
    private CreationControllerFactory ccFactory;
    private SubFormControllerFactory sfcFactory;

    private void init () {
        ccFactory = new CreationControllerFactory();
        sfcFactory = new SubFormControllerFactory();

        myCreationViews = new ArrayList<IObjectCreationView<?>>();

        //String[] subForms = {"Profile", "SmartAI", "Attribute", "Groups" };
        String[] subForms = {"Profile", "UserMover"};


        List<ISubFormControllerSprite> enemiesSfcs =
                sfcFactory.createSpriteSubFormControllers(subForms);
        CreationControllerSprite enemyCC = ccFactory.createSpriteCreationController(enemiesSfcs);
        myCreationViews.add(enemyCC.getMyObjectCreationView());

        List<ISubFormControllerSprite> defendersSfcs =
                sfcFactory.createSpriteSubFormControllers(subForms);
        CreationControllerSprite defenderCC =
                ccFactory.createSpriteCreationController(defendersSfcs);
        myCreationViews.add(defenderCC.getMyObjectCreationView());

        
        String[] attSubForms = { "Attribute" };
        List<ISubFormControllerAttribute> attributeSfcs =
                sfcFactory.createAttributeSubFormControllers(attSubForms);
        CreationControllerAttribute attributeCC =
                ccFactory.createAttributeCreationController(attributeSfcs);
        myCreationViews.add(attributeCC.getMyObjectCreationView());
        

    }

    private InteractionEditorView myInteractionView;
    private WeaponEditorView myWeaponView;

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
