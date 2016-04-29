package gameauthoring.creation.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import engine.IGame;
import gameauthoring.creation.forms.CreationController;
import util.BundleOperations;


/**
 * This is a factory class for the creation of Creation Controllers
 * 
 * @author Jeremy Schreck
 *
 */
public class CreationControllerFactory {

    private IGame myGame;
    private ResourceBundle myCreationClasses = ResourceBundle
            .getBundle("defaults/create_creation_controller");
    private ResourceBundle mySubforms = ResourceBundle.getBundle("defaults/subforms");

    public CreationControllerFactory (IGame game) {
        myGame = game;
    }

    public List<CreationController<?>> createCreationControllers () {
        List<CreationController<?>> ccs = new ArrayList<>();
        List<String> order = BundleOperations.getPropertyValueAsList("Order", myCreationClasses);
        for (String key : order) {
            ccs.add(createCreationController(key));
        }

        return ccs;
    }

    public CreationController<?> createCreationController (String key) {
        String className = myCreationClasses.getString(key);
        List<String> sfcs = getSFCs(key);

        try {
            return (CreationController<?>) Reflection.createInstance(className, key, sfcs, myGame);
        }
        catch (ReflectionException e) {
            System.out.println("reflection exception " + e.getMessage());
            // TODO handle exception
        }
        catch (ClassCastException e) {
            // TODO handle exception
            System.out.println("class cast exception " + e.getMessage());

        }
        return null;
    }

    private List<String> getSFCs (String tabName) {
        return BundleOperations.getPropertyValueAsList(tabName, mySubforms);
    }

}
