package gameauthoring;

import gameauthoring.ITabViewer;
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

    private Tab myCharTab;
    private BorderPane myLayout;
    private AttributeEditorView myAttributeView;
    private DefenderEditorView myDefenderView;
    private EnemyEditorView myEnemyView;
    private InteractionEditorView myInteractionView;
    private WeaponEditorView myWeaponView;

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

        tabpane.getTabs().addAll(myAttributeView.getTab(), myDefenderView.getTab(),
                                 myEnemyView.getTab(), myInteractionView.getTab(),
                                 myWeaponView.getTab());
        return tabpane;
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

        return myLayout;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
    }
}
