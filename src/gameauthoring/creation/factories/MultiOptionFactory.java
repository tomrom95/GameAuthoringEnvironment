// This entire file is part of my masterpiece.
// Joseph Lilien

/**
 * This class is the factory class associated with the MultiSFController abstract class. It utilizes
 * Professor Duvall's reflection util to reflectively generate a new instance of a given class with
 * associated parameters based solely on a string input arguement. These class name strings are
 * defined in proper resource files and map to the associated keys according to the needs of the
 * developer. The relationship established between the MultiSFController class and this Factory
 * allow for full extensibility without ANY violation of the Open-Closed Principle
 * 
 */
package gameauthoring.creation.factories;

import java.util.ResourceBundle;
import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.fire.RemovableSFC;


/**
 * Useful Factory class that uses reflection to generate and return an instance of a RemovableSFC of
 * type T according to parameters passed in as arguments 
 * 
 * @author Joseph Lilien
 *
 * @param <T> type of item to be edited by created RemovableSFCs
 */
public class MultiOptionFactory<T extends IProfilable> extends SubFormControllerFactory<T> {

    private ResourceBundle myDefToSFCs = ResourceBundle.getBundle("defaults/def_to_sfc_classpath");

    public MultiOptionFactory (IGame game) {
        super(game);
    }

    @SuppressWarnings("unchecked")
    // Cast to RemvoableSFC is necessary due to nature of Reflection, all ClassCastExceptions are
    // caught and handled appropriately
    @Override
    public RemovableSFC<T> createSubFormController (String className,
                                                    Object ... params) {
        String errorMsg =
                String.format("Can't create MultiOption subformcontroller of class name %s from definition %s",
                              getMyDefToSFCs().getString(className), className);
        try {
            return (RemovableSFC<T>)Reflection
                    .createInstance(getMyDefToSFCs().getString(className), params);
        }
        catch (ReflectionException e) {
            String message =
                    String.format("%s\nReflection exception: %s.", errorMsg, e.getMessage());
            throw new ReflectionException(message);
        }
        catch (ClassCastException e) {
            String message =
                    String.format("%s\nClass cast exception: %s.", errorMsg, e.getMessage());
            throw new ClassCastException(message);
        }
    }

    private ResourceBundle getMyDefToSFCs () {
        return myDefToSFCs;
    }

}
