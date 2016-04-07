package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;
import engine.ISprite;
import gameauthoring.IObjectListView;
import gameauthoring.ObjectListView;

/**
 * This class will be in charge of reading an xml file that specifies which
 * subformviews and subformcontrollers to use for each FormView/CreationController.
 * It will then generate the appropriate classes reflectively and set up the 
 * necessary connections between them
 * 
 * Right now I'm skipping the xml part and just using this as an example of how
 * we may set up connections between subformviews and subformcontrollers
 * 
 * @author Jeremy Schreck
 *
 */
public class XMLParser {

    public XMLParser () {
        // Parse xml file to figure out which tabs to create and how to create them
        
        // Example: xml says to create an enemies tab
        setupCreationFormAndListViewStructure();
    }
    
    /**
     * Sets up everything contained in one of our ListView/Form components of 
     * the frontend. This will be called many times, since we reuse this structure
     * throughout the frontend.
     * 
     * ex: do this for each of Enemies, Defenders, Terrain, Obstacle tabs as well
     * as Projectiles, Interactions, Attributes tabs.
     */
    private void setupCreationFormAndListViewStructure () {
        // Example for Enemies tab
        
        
        ProfileSubFormView profileSubFormView = new ProfileSubFormView();
        ISubFormController profileSubFormController = new ProfileSubFormController(profileSubFormView);
        
        List<SubFormView> subFormViews = new ArrayList<SubFormView>();
        subFormViews.add(profileSubFormView);
        
        IFormView enemiesFormView = new FormView(subFormViews);
        IObjectListView enemiesListView = new ObjectListView();
        IObjectCreationView enemiesObjectCreationView = new ObjectCreationView(enemiesListView, enemiesFormView);
        
        
        ICreationController<ISprite> enemiesCreationController = new CreationController<ISprite>();
        enemiesCreationController.setObjectCreationView(enemiesObjectCreationView);
        enemiesCreationController.addSubFormController(profileSubFormController);
        
        
    }
    
    private void createSubFormViews() {
        // for loop will be determined by xml
        
        
        
    }

}
