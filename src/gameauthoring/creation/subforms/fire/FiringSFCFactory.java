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

    private ResourceBundle myResources = ResourceBundle.getBundle("firingDefSFCMap");

    /**
     * Constructor 
     */
    public FiringSFCFactory () {
    }
    
    public RemovableSpriteSFC createSubFormController (String definitionClassName, IGame game, FiringSFCmult firingSFC) {
        
        FirerDefinition firingDef;
        try{
            firingDef = (FirerDefinition) Reflection.createInstance(definitionClassName, game);
        } catch  (ReflectionException | ClassCastException e ){
            throw e;
        } 
        
        return this.createSubFormController(getMyResources().getString(definitionClassName), game, firingSFC, firingDef);
        /*
        if (type.equals("DIRECTIONAL")) {
            return new DirectionalFireSFC(getMyGame(), getMyFiringSFC(), new DirectionalFirerDefinition(getMyGame()));
        }
        else if (type.equals("TRACKING")) {
            return new TrackingFireSFC(getMyGame(), getMyFiringSFC(), new TrackingFirerDefinition(getMyGame()));
        }
        */
        
    }
    
    public RemovableSpriteSFC createSubFormController (IGame game, FiringSFCmult firingSFC, FirerDefinition firerDef) {
      
        return this.createSubFormController(getMyResources().getString(firerDef.getClass().getName()), game, firingSFC, firerDef);
       
        
    }

 

    public RemovableSpriteSFC createSubFormController (String definitionClassName, Object ... params) {
        
        try{
            return (RemovableSpriteSFC) Reflection.createInstance(getMyResources().getString(definitionClassName),  params);
        } catch (ReflectionException | ClassCastException e ){
            //TODO should this be error message or just throw error?
            String errorMsg = "Check your properties files. Unable to create firing subformcontroller with className " + definitionClassName + " and params " + params;
            ErrorMessage errorMessage = new ErrorMessage(errorMsg);
            errorMessage.showError();
            throw e;
        }
       
    }
    
    private ResourceBundle getMyResources() {
        return myResources;
    }
    
    
/*
    @Override
    public RemovableSpriteSFC createSubFormController (String type) {
        if (type.equals("DIRECTIONAL")) {
            return new DirectionalFireSFC(getMyGame(), getMyFiringSFC(), new DirectionalFirerDefinition(getMyGame()));
        }
        else if (type.equals("TRACKING")) {
            return new TrackingFireSFC(getMyGame(), getMyFiringSFC(), new TrackingFirerDefinition(getMyGame()));
        }
        return null;
    }

    private FiringSFCmult getMyFiringSFC () {
        return myFiringSFC;
    }

    @Override
    public RemovableSpriteSFC createSubFormController (String type, Object ... params) {
        if (type.equals("DIRECTIONAL")) {
            return new DirectionalFireSFC(getMyGame(), getMyFiringSFC(), (DirectionalFirerDefinition) params[0]);
        }
        else if (type.equals("TRACKING")) {
            return new TrackingFireSFC(getMyGame(), getMyFiringSFC(),  (TrackingFirerDefinition) params[0]);
        }
        return null;
    }
    */
    
    
}
