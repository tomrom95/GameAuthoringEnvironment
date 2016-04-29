package gameauthoring.creation.subforms.fire;

import java.util.ResourceBundle;
import engine.IGame;
import gameauthoring.creation.factories.Reflection;
import gameauthoring.creation.factories.ReflectionException;
import engine.definitions.moduledef.FirerDefinition;
import gameauthoring.util.ErrorMessage;


/**
 * 
 * This is a factory class that creates different types of firing sub-subforms,
 * which the FiringSFC will dynamically switch between based on user input
 * 
 * @author Jeremy Schreck
 * @author Joe Lilien
 *
 */
public class FiringSFCFactory {

    private ResourceBundle myDefToSFCs = ResourceBundle.getBundle("defaults/def_to_sfc_classpath");
    private ResourceBundle myDefClasspaths = ResourceBundle.getBundle("defaults/sfc_classpath");

    /**
     * Constructor
     */
    public FiringSFCFactory () {

    }

    public RemovableSpriteSFC createSubFormController (String type,
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

    public RemovableSpriteSFC createSubFormController (IGame game,
                                                       FiringSFC firingSFC,
                                                       FirerDefinition firerDef) {

        return this.createSubFormController(
                                            getMyDefToSFCs()
                                                    .getString(firerDef.getClass().getName()),
                                            game, firingSFC, firerDef);

    }

    public RemovableSpriteSFC createSubFormController (String className, Object ... params) {

        try {
            return (RemovableSpriteSFC) Reflection.createInstance(className, params);
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

    /*
     * @Override
     * public RemovableSpriteSFC createSubFormController (String type) {
     * if (type.equals(DirectionalFirerDefinition.class.getName())) {
     * return new DirectionalFireSFC(getMyGame(), getMyFiringSFC(), new
     * DirectionalFirerDefinition(getMyGame()));
     * }
     * else if (type.equals(TrackingFirerDefinition.class.getName())) {
     * return new TrackingFireSFC(getMyGame(), getMyFiringSFC(), new
     * TrackingFirerDefinition(getMyGame()));
     * }
     * else if (type.equals(UserFirerDefinition.class.getName())) {
     * return new UserFireSFC(getMyGame(), getMyFiringSFC(), new UserFirerDefinition(getMyGame()));
     * }
     * return null;
     * }
     * 
     * @Override
     * public RemovableSpriteSFC createSubFormController (String type, Object ... params) {
     * 
     * if (type.equals(DirectionalFirerDefinition.class.getName())) {
     * return new DirectionalFireSFC(getMyGame(), getMyFiringSFC(), (DirectionalFirerDefinition)
     * params[0]);
     * }
     * else if (type.equals(TrackingFirerDefinition.class.getName())) {
     * return new TrackingFireSFC(getMyGame(), getMyFiringSFC(), (TrackingFirerDefinition)
     * params[0]);
     * }
     * else if (type.equals(UserFirerDefinition.class.getName())) {
     * return new UserFireSFC(getMyGame(), getMyFiringSFC(), (UserFirerDefinition) params[0]);
     * }
     * return null;
     * }
     */

}
