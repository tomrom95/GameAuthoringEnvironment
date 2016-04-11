package gameauthoring;

import gameauthoring.ITabViewer;
import gameauthoring.conditiontab.ConditionView;
import gameauthoring.object_creation_tab.AttributeEditorView;
import gameauthoring.object_creation_tab.DefenderEditorView;
import gameauthoring.object_creation_tab.EnemyEditorView;
import gameauthoring.object_creation_tab.InteractionEditorView;
import gameauthoring.object_creation_tab.WeaponEditorView;
import java.util.List;
import engine.ICondition;
import engine.OnClickCondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private Tab myCharTab;
    private BorderPane myLayout;
    private AttributeEditorView myAttributeView;
    private DefenderEditorView myDefenderView;
    private EnemyEditorView myEnemyView;
    private InteractionEditorView myInteractionView;
    private WeaponEditorView myWeaponView;
    private ListDisplay<ICondition> myConditionView;

    public ObjectCreationTabViewer (Tab charTab) {
        myCharTab = charTab;
        TabPane tabPane = createAllSubTabs();
        myCharTab.setContent(tabPane);
    }

    private TabPane createAllSubTabs () {
        TabPane tabpane = new TabPane();
        myAttributeView = new AttributeEditorView(createSubTab("Attributes"));
        myDefenderView = new DefenderEditorView(createSubTab("Defenders"));
        myEnemyView = new EnemyEditorView(createSubTab("Enemies"));
        myInteractionView = new InteractionEditorView(createSubTab("Interactions"));
        myWeaponView = new WeaponEditorView(createSubTab("Weapons"));
        myConditionView = new ConditionView(createSubTab("Conditions"), getTestConditions(), getOptions());

        tabpane.getTabs().addAll(myAttributeView.getTab(), myDefenderView.getTab(),
                                 myEnemyView.getTab(), myInteractionView.getTab(),
                                 myWeaponView.getTab(), myConditionView.getTab());
        return tabpane;
    }

    private ObservableList<String> getOptions () {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Ryan");
        return options;
    }

    private ObservableList<ICondition> getTestConditions () {
        ObservableList<ICondition> conditions = FXCollections.observableArrayList();
        ICondition c = new OnClickCondition(null, null, null, null);
        conditions.add(c);
        return conditions;
    }

    private Tab createSubTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }

    List<ObjectCreationView> getObjectCreationView () {
        return null;
    }

    public Tab getTab () {
        return myCharTab;
    }

    @Override
    public Node draw () {
        myLayout = new BorderPane();
        myLayout = (BorderPane) myConditionView.draw();
        return myLayout;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}
