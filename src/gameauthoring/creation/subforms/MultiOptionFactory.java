package gameauthoring.creation.subforms;

import java.util.ResourceBundle;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.FirerDefinition;
import engine.profile.IProfilable;
import gameauthoring.creation.factories.Reflection;
import gameauthoring.creation.factories.ReflectionException;
import gameauthoring.creation.factories.SubFormControllerFactory;
import gameauthoring.creation.subforms.fire.FiringSFC;
import gameauthoring.creation.subforms.fire.RemovableSFC;
import gameauthoring.util.ErrorMessage;

public class MultiOptionFactory<T extends IProfilable>  extends SubFormControllerFactory<T>{
    private ResourceBundle myDefToSFCs = ResourceBundle.getBundle("defaults/def_to_sfc_classpath");

    public MultiOptionFactory (IGame game) {
        super(game);
    }

    @Override
    protected RemovableSFC<T> createSubFormController (String className,
                                                                         Object ... params) {

        try {
            Object test = Reflection.createInstance(getMyDefToSFCs()
                                                    .getString(className), params);
            return (RemovableSFC<T>) Reflection.createInstance(getMyDefToSFCs().getString(className), params);

        }
        catch (ReflectionException | ClassCastException e) {
            // TODO should this be error message or just throw error?
            String errorMsg = "Check your properties files. Unable to create firing subformcontroller with className " +
                              className + " and params " + arrayToString(params);
            System.out.println(errorMsg);
            ErrorMessage errorMessage = new ErrorMessage(errorMsg);
            errorMessage.showError();
            throw e;
        }
    }
    
    private String arrayToString (Object[] objects) {
        String x = "[ ";
        for (Object o : objects) {
            x += o.toString();
            x += " ";
        }
        x += "]";
        return x;
    }

    private ResourceBundle getMyDefToSFCs () {
        return myDefToSFCs;
    }

}
