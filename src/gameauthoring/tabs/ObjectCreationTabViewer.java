package gameauthoring.tabs;

import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.CreationControllerAttribute;
import gameauthoring.creation.forms.CreationControllerFactory;
import gameauthoring.creation.forms.CreationControllerSprite;
import gameauthoring.creation.forms.IObjectCreationView;
import gameauthoring.creation.forms.ObjectCreationView;
import gameauthoring.creation.subforms.AttributeSubFormController;
import gameauthoring.creation.subforms.ISubFormControllerAttribute;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.MovementSubFormController;
import gameauthoring.creation.subforms.ProfileSubFormController;
import gameauthoring.creation.subforms.SubFormControllerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.AuthorshipData;
import engine.Game;
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
    private AuthorshipData myAuthorshipData;

    private List<CreationController<?>> myCCs =
            new ArrayList<CreationController<?>>(Arrays
                    .asList(new CreationControllerSprite(myEnemySFC, myAuthorshipData),
                            new CreationControllerSprite(myDefenderSFC, myAuthorshipData),
                            new CreationControllerAttribute(myAttributeSFC, myAuthorshipData)));
    private List<IObjectCreationView<?>> myCVs;

    
    public ObjectCreationTabViewer () {
        init();
    }

    public ObjectCreationTabViewer (Game game) {
        myAuthorshipData = game.getAuthorshipData();
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

    private AuthorshipData getMyAuthorshipData () {
        return myAuthorshipData;
    }

   
}