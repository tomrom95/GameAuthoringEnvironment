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
            String message =
                    String.format("Reflection exception for creation controller key %s with subforms %s:\n%s ",
                                  key, sfcs, e.getMessage());
            throw new ReflectionException(message);
        }
        catch (ClassCastException e) {
            String message =
                    String.format("Class cast exception for creation controller key %s with subforms %s:\n" +
                                  "%s ", key, sfcs, e.getMessage());
            throw new ReflectionException(message);

        }
    }

    private List<String> getSFCs (String tabName) {
        return BundleOperations.getPropertyValueAsList(tabName, mySubforms);
    }

}
