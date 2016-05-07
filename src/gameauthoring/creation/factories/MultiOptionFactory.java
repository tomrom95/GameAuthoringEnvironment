package gameauthoring.creation.factories;

import java.util.ResourceBundle;
import engine.IGame;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.fire.RemovableSFC;


public class MultiOptionFactory<T extends IProfilable> extends SubFormControllerFactory<T> {
    private ResourceBundle myDefToSFCs = ResourceBundle.getBundle("customization/def_to_sfc_classpath");

    public MultiOptionFactory (IGame game) {
        super(game);
    }

    @Override
    public RemovableSFC<T> createSubFormController (String className,
                                                    Object ... params) {
        String errorMsg =
                String.format("Can't create MultiOption subformcontroller of class name %s from definition %s",
                              getMyDefToSFCs().getString(className), className);

        try {
            return (RemovableSFC<T>) Reflection
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
