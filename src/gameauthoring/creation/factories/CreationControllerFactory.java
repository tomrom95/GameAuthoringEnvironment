// This entire file is part of my masterpiece.
// Jeremy Schreck
/*
 * 
 * 
 * 
 */
package gameauthoring.creation.factories;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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

    private ResourceBundle myCreationControllersBundle = ResourceBundle
            .getBundle("customization/creation_controllers");
    private ResourceBundle mySubformsBundle = ResourceBundle.getBundle("customization/subforms");
    private IGame myGame;

    /**
     * Constructor
     * 
     * @param game The current game object
     */
    public CreationControllerFactory (IGame game) {
        myGame = game;
    }

    /**
     * Returns a list of creation controllers based off of a resource file
     * specifying which ones to create
     * 
     * @return The list of creation controller objects
     */
    public List<CreationController<?>> createCreationControllers () {
        List<String> order =
                BundleOperations.getPropertyValueAsList("Order", myCreationControllersBundle);
        return order.stream().map(key -> createCreationController(key))
                .collect(Collectors.toList());
    }

    /**
     * Creates a single creation controller with both its class and subforms to instantiate
     * being specified in resource files
     * 
     * @param key The id of the creation controller, used to retrieve info from resource files
     * @return The creation controller object
     */
    private CreationController<?> createCreationController (String key) {
        String className = myCreationControllersBundle.getString(key);
        List<String> sfcs = getSFCs(key);
        String errorMsg =
                String.format("exception for creation controller key %s with subforms %s:\n", key,
                              sfcs);

        try {
            return (CreationController<?>) Reflection.createInstance(className, key, sfcs, myGame);
        }
        catch (ReflectionException e) {
            String message = String.format("Reflection %s %s", errorMsg, e.getMessage());
            throw new ReflectionException(message);
        }
        catch (ClassCastException e) {
            String message = String.format("Class cast %s %s ", errorMsg, e.getMessage());
            throw new ReflectionException(message);

        }
    }

    /**
     * Gets the subforms that a creation controller should instantiate
     * 
     * @param key The id of the creation controller, used to retrieve info from a resource file
     * @return A list of string IDs specifying the subforms
     */
    private List<String> getSFCs (String key) {
        return BundleOperations.getPropertyValueAsList(key, mySubformsBundle);
    }

}
