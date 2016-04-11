package gameauthoring;

import gameauthoring.ITabViewer;
import gameauthoring.characters.CreationControllerFactory;
import gameauthoring.characters.CreationControllerSprite;
import gameauthoring.characters.IObjectCreationView;
import gameauthoring.characters.ISubFormControllerSprite;
import gameauthoring.characters.SubFormControllerFactory;
import gameauthoring.object_creation_tab.InteractionEditorView;
import gameauthoring.object_creation_tab.WeaponEditorView;
import java.util.ArrayList;
import java.util.List;
import engine.ICondition;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * Character(Sprite) tab view class which allows users to create all weapons, enemies, defenders,
 * obstacles, and interactions. Handles selection between these sub-tabs. Serves to make creation
 * controller and subform controller using factory design.
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

        String[] subForms = { "profile", "movement", "attributes", "groups" };

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
         * String[] attSubForms = { "attribute" };
         * List<ISubFormControllerAttribute> attributeSfcs =
         * sfcFactory.createAttributeSubFormControllers(subForms);
         * CreationControllerAttribute attributeCC =
         * ccFactory.createAttributeCreationController(attributeSfcs);
         * myCreationViews.add(defenderCC.getMyObjectCreationView());
         */

    }

    private InteractionEditorView myInteractionView;
    private WeaponEditorView myWeaponView;
    private ListDisplay<ICondition> myConditionView;

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
         * myInteractionView = new InteractionEditorView(createSubTab("Interactions"));
         * myWeaponView = new WeaponEditorView(createSubTab("Weapons"));
         * myConditionView = new ConditionView(createSubTab("Conditions"), getTestConditions(),
         * getOptions());
         * 
         * tabpane.getTabs().addAll(myAttributeView.getTab(), myDefenderView.getTab(),
         * myEnemyView.getTab(), myInteractionView.getTab(),
         * <<<<<<< HEAD
         * myWeaponView.getTab(), myConditionView.getTab());
         * return tabpane;
         * }
         * 
         * private ObservableList<String> getOptions () {
         * ObservableList<String> options = FXCollections.observableArrayList();
         * options.add("Ryan");
         * return options;
         * }
         * 
         * private ObservableList<ICondition> getTestConditions () {
         * ObservableList<ICondition> conditions = FXCollections.observableArrayList();
         * ICondition c = new OnClickCondition(null, null, null, null);
         * conditions.add(c);
         * return conditions;
         * }
         * 
         * private Tab createSubTab (String tabName) {
         * Tab newTab = new Tab();
         * newTab.setText(tabName);
         * return newTab;
         * }
         * 
         * List<ObjectCreationView> getObjectCreationView () {
         * return null;
         * }
         * 
         * public Tab getTab () {
         * return myCharTab;
         * }
         * 
         * @Override
         * public Node draw () {
         * myLayout = new BorderPane();
         * myLayout = (BorderPane) myConditionView.draw();
         * return myLayout;
         * =======
         * myWeaponView.getTab());
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
