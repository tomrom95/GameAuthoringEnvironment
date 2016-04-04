package gameauthoring;

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
public class CharTabViewer implements ITabViewer {

    private Tab myCharTab;
    private BorderPane myLayout;

    public CharTabViewer (Tab charTab) {
        myCharTab = charTab;
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(createAllSubTabs());
        myCharTab.setContent(tabPane);
    }

    private List<Tab> createAllSubTabs () {
        List<Tab> allSubTabs = new ArrayList<Tab>();
        Tab attributesTab = createSubTab("Attributes");
        allSubTabs.add(attributesTab);
        Tab weaponsTab = createSubTab("Weapons");
        allSubTabs.add(weaponsTab);
        Tab enemiesTab = createSubTab("Enemies");
        allSubTabs.add(enemiesTab);
        Tab defendersTab = createSubTab("Defenders");
        allSubTabs.add(defendersTab);
        Tab interactionsTab = createSubTab("Interactions");
        allSubTabs.add(interactionsTab);
        return allSubTabs;
    }

    private Tab createSubTab (String tabName) {
        Tab newTab = new Tab();
        newTab.setText(tabName);
        return newTab;
    }

    List<ObjectCreationView> getObjectCreationView () {
        return null;
    }
    
    public Tab getTab(){
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
