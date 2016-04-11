package gameauthoring;

import gameauthoring.ITabViewer;
import gameauthoring.characters.AttributeSubFormController;
import gameauthoring.characters.CreationController;
import gameauthoring.characters.CreationControllerAttribute;
import gameauthoring.characters.CreationControllerFactory;
import gameauthoring.characters.CreationControllerSprite;
import gameauthoring.characters.IObjectCreationView;
import gameauthoring.characters.ISubFormControllerAttribute;
import gameauthoring.characters.ISubFormControllerSprite;
import gameauthoring.characters.MovementSubFormController;
import gameauthoring.characters.ObjectCreationView;
import gameauthoring.characters.ProfileSubFormController;
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
 *
 */
public class ObjectCreationTabViewer implements ITabViewer {

    private TabPane myTabPane;

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
    }

    private void generateAllSubTabs () {  
        for (int i=0;i<myCCs.size();i++) {
            Tab tab = new Tab(myTitles.get(i));
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
}
