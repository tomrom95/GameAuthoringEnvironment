package gameauthoring.reflectiontesting;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.FormView;
import gameauthoring.creation.forms.IFormView;
import gameauthoring.creation.forms.ICreationView;
import gameauthoring.creation.forms.ICreationListView;
import gameauthoring.creation.forms.CreationView;
import gameauthoring.creation.forms.CreationListView;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.ProfileSFC;
import gameauthoring.creation.subforms.ProfileSFV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        //setupCreationFormAndListViewStructure();
    }
    
    /**
     * Sets up everything contained in one of our ListView/Form components of 
     * the frontend. This will be called many times, since we reuse this structure
     * throughout the frontend.
     * 
     * ex: do this for each of Enemies, Defenders, Terrain, Obstacle tabs as well
     * as Projectiles, Interactions, Attributes tabs.
     */
    private void setupCreationFormAndListViewStructure () throws ReflectiveOperationException {
        
        /*
         * for each <creationTab>
         *      itemType = <itemType>
         *      create list of objects
         *      create new list of subformviews
         *      create new list of subformcontrollers
         *      for each <subform> in <subforms>
         *              - create new ISubFormView reflectively from <view>, add to list
         *                      - add to subformviews list
         *              - create new ISubFormController<itemType> reflectively from <controller>, add to list
         *                      - set its view to <view>
         *                      - add to subformcontrollers list
         *                      
         *              - note: order of lists don't need to match (though they probably will). 
         *                      dependency is only that controller is matched to its view
         *      create IObjectCreationView
         *              - set its name to <name>
         *              - set its formview's list of subformviews 
         *              - option: can create its formview and objectlistview and pass it, 
         *              or can have the Creation view create those views itself
         *      create ICreationController<itemType>
         *              - set its view to IObjectCreationView
         *              - set its list of subcontrollers
         *              
         */
        
        // for each creation tab in xml
            String name = "Enemies";
            String itemClassName = "ISprite"; //maps to SpriteCreationController, SpriteObservableList, SpriteObjectListView, SpriteObjectCreationView
            
            List<ISubFormView> subFormViews = new ArrayList<ISubFormView>();
            List<ISubFormController<?>> subFormControllers = new ArrayList<ISubFormController<?>>();
            
            //for each <subform> in <subforms>
                String subFormName = "Profile"; //maps to ProfileController, ProfileView (or one them is instantiated in the other)
                String subFormControllerName = "authoring.ProfileSubFormController";
                String subFormViewName = "authoring.ProfileSubFormView";

                String arrangementClass = "Arrangement 1";
                
                Class<?> subFormViewClass = Class.forName(subFormViewName);
                ISubFormView subFormView = (ISubFormView) subFormViewClass.newInstance();
                
                Class<?> subFormControllerClass = Class.forName(subFormControllerName);
                Constructor<?> ctor = subFormControllerClass.getDeclaredConstructor(ISubFormView.class);
                ISubFormController<?> subFormController = (ISubFormController<?>) ctor.newInstance(subFormView);
                
                
                
                subFormControllers.add(subFormController);
                subFormViews.add(subFormView);
                
           //end for
            String creationViewName = "authoring.SpriteObjectCreationView";
            Class<?> creationViewClass = Class.forName(creationViewName);
            Constructor<?> creationViewCtor = creationViewClass.getDeclaredConstructor(List.class);
            ICreationView<?> creationView = (ICreationView<?>) creationViewCtor.newInstance(subFormViews);
            
            String creationControllerName = "authoring.SpriteCreationController";
            Class<?> creationControllerClass = Class.forName(itemClassName);
            Constructor<?> creationControllersCtor = creationControllerClass.getDeclaredConstructor(ICreationView.class, ISubFormController.class);
            //ICreationController<?> creationController = (ICreationController<?>) creationControllersCtor.newInstance(creationView, subFormControllers);
            
        //endfor 
            
            
        
        
        
        // Example for Enemies tab
        // itemType = ISprite
        // subformview = ProfileSubFormView
        // subformcontroller = ProfileSubFormController
        //String name = "Enemies"; 
        //List<ISubFormView> subFormViews = new ArrayList<ISubFormView>();
        //List<ISubFormController<ISprite>> subFormControllers = new ArrayList<ISubFormController<ISprite>>();
        
        ProfileSFV profileSubFormView = new ProfileSFV(); //TODO: how to make this general but enforce dependenc?
        subFormViews.add(profileSubFormView);

        //ISubFormController<ISprite> profileSubFormController = new ProfileSubFormController<ISprite>(profileSubFormView);
        //subFormControllers.add(profileSubFormController);
        
        
        // TODO: options here for whether setup occurs here (to be able to do reflectively and customize) vs setup
        // in IObjectCreationView (so we don't have to specify as much in the xml)
        IFormView enemiesFormView = new FormView(subFormViews);
        ObservableList<ISprite> enemiesList = FXCollections.observableArrayList();
        //IObjectListView<ISprite> enemiesListView = new ObjectListView<ISprite>(enemiesList);
        //IObjectCreationView<ISprite> enemiesCreationView = new ObjectCreationView<ISprite>(enemiesListView, enemiesFormView);
        //enemiesCreationView.setTitle(name);
        
        /*
         *  Set up CreationController
         *      - connect to CreationView
         *      - give it appropriate subcontrollers specified in xml
         *      - 
         */
        //ICreationController<ISprite> enemiesCreationController = new CreationController<ISprite>();
        //enemiesCreationController.setObjectCreationView(enemiesCreationView);
        //enemiesCreationController.setSubFormControllers(subFormControllers);
        
        Factory<Sprite> enemiesFactory = new ItemFactory<Sprite>(Sprite.class);
        //enemiesCreationController.setFactory(enemiesFactory);

        
    }

}
