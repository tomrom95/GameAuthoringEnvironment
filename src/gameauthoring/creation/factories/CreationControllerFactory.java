package gameauthoring.creation.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.IGame;
import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.CreationControllerAttribute;
import gameauthoring.creation.forms.CreationControllerEvent;
import gameauthoring.creation.forms.CreationControllerGlobals;
import gameauthoring.creation.forms.CreationControllerGroup;
import gameauthoring.creation.forms.CreationControllerMissile;
import gameauthoring.creation.forms.CreationControllerSprite;


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
        List<String> order = getPropertyValueAsList("Order", myCreationClasses);
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
        System.out.println(className);
        return null;
    }

    private List<String> getCommaSeparatedStringAsList(String value) {
        return Arrays.asList(value.split(",")).stream()
                .map(sfc -> sfc.trim()).collect(Collectors.toList());
    }
    private List<String> getPropertyValueAsList(String key, ResourceBundle resource ){
        return  getCommaSeparatedStringAsList(resource.getString(key));
    }
    private List<String> getSFCs (String tabName) {
        return getPropertyValueAsList(tabName, mySubforms);
    }

}
