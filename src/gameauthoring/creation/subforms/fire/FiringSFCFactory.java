package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import engine.IGame;
import gameauthoring.creation.factories.Reflection;
import gameauthoring.creation.factories.ReflectionException;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.FirerDefinition;
import gameauthoring.util.ErrorMessage;


import gameauthoring.creation.subforms.MultiOptionFactory;

/**
 * 
 * This is a factory class that creates different types of firing sub-subforms,
 * which the FiringSFC will dynamically switch between based on user input
 * 
 * @author Jeremy Schreck
 * @author Joe Lilien
 *
 */

public class FiringSFCFactory extends MultiOptionFactory<SpriteDefinition> {

    private ResourceBundle myDefToSFCs = ResourceBundle.getBundle("defaults/def_to_sfc_classpath");
    private ResourceBundle myDefClasspaths = ResourceBundle.getBundle("defaults/sfc_classpath");

    /**
     * Constructor
     */
    public FiringSFCFactory (IGame game) {
        super(game);
    }

    public RemovableSFC<SpriteDefinition> createSubFormController (String type,
                                                       IGame game,
                                                       FiringSFC firingSFC) {

        String definitionClassName = getMyDefClasspaths().getString(type);
        FirerDefinition firingDef;
        try {
            firingDef = (FirerDefinition) Reflection.createInstance(definitionClassName, game);

        }
        catch (ReflectionException | ClassCastException e) {
            String errorMsg = "Check your properties files. Unable to create firing definition with className " +
                              definitionClassName + " from type " + type;
            ErrorMessage errorMessage = new ErrorMessage(errorMsg);
            errorMessage.showError();
            throw e;
        }

        return this.createSubFormController(getMyDefToSFCs().getString(definitionClassName), game, firingSFC, firingDef);

    }

    public RemovableSFC<SpriteDefinition> createSubFormController (String className, IGame game,
                                                       FiringSFC firingSFC,
                                                       FirerDefinition firerDef) {

        return this.createSubFormController(
                                            getMyDefToSFCs()
                                                    .getString(firerDef.getClass().getName()),
                                            game, firingSFC, firerDef);

    }

    protected RemovableSFC<SpriteDefinition> createSubFormController (String className, Object ... params) {

        try {
            Object test = Reflection.createInstance(className, params);
            return (RemovableSFC<SpriteDefinition>) Reflection.createInstance(className, params);

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

    private ResourceBundle getMyDefClasspaths () {
        return myDefClasspaths;
    }

}
