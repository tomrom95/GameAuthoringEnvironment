package gameauthoring;

import gameauthoring.ITabViewer;
<<<<<<< HEAD
=======
import gameauthoring.characters.AttributeSubFormController;
import gameauthoring.characters.CreationController;
import gameauthoring.characters.CreationControllerAttribute;
>>>>>>> 5c956e7a2a513d379853c626b1bd2924f5d9bd91
import gameauthoring.characters.CreationControllerFactory;
import gameauthoring.characters.CreationControllerSprite;
import gameauthoring.characters.IObjectCreationView;
import gameauthoring.characters.ISubFormControllerSprite;
<<<<<<< HEAD
=======
import gameauthoring.characters.MovementSubFormController;
import gameauthoring.characters.ObjectCreationView;
import gameauthoring.characters.ProfileSubFormController;
>>>>>>> 5c956e7a2a513d379853c626b1bd2924f5d9bd91
import gameauthoring.characters.SubFormControllerFactory;
import gameauthoring.object_creation_tab.InteractionEditorView;
import gameauthoring.object_creation_tab.WeaponEditorView;
import java.util.ArrayList;
import java.util.Arrays;
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

<<<<<<< HEAD
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

=======
    private List<String> myTitles = new ArrayList<String>(Arrays.asList("Enemies", "Defenders", "Attributes"));
    private List<ISubFormControllerSprite> myEnemySFC =
            new ArrayList<ISubFormControllerSprite>(Arrays.asList(new ProfileSubFormController()));
    private List<ISubFormControllerSprite> myDefenderSFC =
            new ArrayList<ISubFormControllerSprite>(Arrays.asList(new ProfileSubFormController()));
    private List<ISubFormControllerAttribute> myAttributeSFC =
            new ArrayList<ISubFormControllerAttribute>(Arrays
                    .asList(new AttributeSubFormController()));

    private List<CreationController<?>> myCCs =
            new ArrayList<CreationController<?>>(Arrays
                    .asList(new CreationControllerSprite(myEnemySFC),
                            new CreationControllerSprite(myDefenderSFC),
                            new CreationControllerAttribute(myAttributeSFC)));
    private List<IObjectCreationView<?>> myCVs;

    
>>>>>>> 5c956e7a2a513d379853c626b1bd2924f5d9bd91
    public ObjectCreationTabViewer () {
        init();
    }

    private void init () {      
        myTabPane = new TabPane();
        generateCreationViewList();
        generateAllSubTabs();
    }


    private void generateCreationViewList () {
        myCVs = new ArrayList<IObjectCreationView<?>>();
        for(CreationController<?> cc:myCCs){          
            myCVs.add(cc.getMyObjectCreationView());
        }
<<<<<<< HEAD
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
=======
    }

    private void generateAllSubTabs () {  
        for (int i=0;i<myCCs.size();i++) {
            Tab tab = new Tab(myTitles.get(i));
            tab.setContent(myCVs.get(i).draw());
            myTabPane.getTabs().add(tab);
        }
>>>>>>> 5c956e7a2a513d379853c626b1bd2924f5d9bd91
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
