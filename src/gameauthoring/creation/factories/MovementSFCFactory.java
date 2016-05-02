package gameauthoring.creation.factories;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.AIPatherDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import engine.definitions.moduledef.MovementDefinition;
import engine.definitions.moduledef.PathMoverDefinition;
import engine.definitions.moduledef.StaticMovementDefintion;
import engine.definitions.moduledef.TrackingMoverDefinition;
import engine.definitions.moduledef.UserMoverDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.movement.ConstantMoverSFC;
import gameauthoring.creation.subforms.movement.PathMoverSFC;
import gameauthoring.creation.subforms.movement.SmartAIMovementSFC;
import gameauthoring.creation.subforms.movement.StaticMoverSFC;
import gameauthoring.creation.subforms.movement.TrackingMoverSFC;
import gameauthoring.creation.subforms.movement.UserMoverSFC;


/**
 * This is a factory class that creates different types of movement sub-subforms,
 * which the MovementSFC will dynamically switch between based on user input
 * 
 * @author Jeremy Schreck
 *
 */
public class MovementSFCFactory extends DynamicSFCFactory<SpriteDefinition> {

    public MovementSFCFactory (IGame game) {
        super(game);

    }

    @Override
    public ISubFormControllerSprite createSubFormController (String type, Object ... params) {
        if (type.equals("SMARTAIMOVER")) {

            return new SmartAIMovementSFC(getMyGame());
        }
        else if (type.equals("STATICMOVER")) {
            return new StaticMoverSFC();

        }
        else if (type.equals("CONSTANTMOVER")) {
            return new ConstantMoverSFC();

        }
        else if (type.equals("USERMOVER")) {
            return new UserMoverSFC();

        }
        else if (type.equals("TRACKINGMOVER")) {
            return new TrackingMoverSFC(getMyGame());

        }else if (type.equals("PATHMOVER")){
            return new PathMoverSFC(); 
        }
        throw new ReflectionException("Can't create movement sub-subformcontroller of type " + type);

    }

    public ISubFormController<SpriteDefinition> getSFCFromDefinition (MovementDefinition movDef) {
        if (movDef instanceof StaticMovementDefintion){
            return(new StaticMoverSFC());
            
        }else if (movDef instanceof ConstantMoverDefinition){
            return(new ConstantMoverSFC());
            
        }else if (movDef instanceof UserMoverDefinition){
            return(new UserMoverSFC());
            
        }else if (movDef instanceof TrackingMoverDefinition){
            return(new TrackingMoverSFC(getMyGame()));
            
        }else if (movDef instanceof PathMoverDefinition){
            return(new PathMoverSFC());
            
        } else if (movDef instanceof AIPatherDefinition){
            return (new SmartAIMovementSFC(getMyGame()));
        }
        throw new ReflectionException("No defined movement SFC matches given definition");
    }

}
