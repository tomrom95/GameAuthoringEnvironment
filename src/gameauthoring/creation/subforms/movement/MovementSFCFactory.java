package gameauthoring.creation.subforms.movement;

import engine.IGame;

import gameauthoring.creation.factories.DynamicSFCFactory;
import gameauthoring.creation.factories.Reflection;
import gameauthoring.creation.factories.ReflectionException;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;


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
    public ISubFormControllerSprite createSubFormController (String type) {
        if (type.equals("SMARTAIMOVER")) {

            return new SmartAIMovementSFC();
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

        }

        return null;
    }

    @Override
    public ISubFormControllerSprite createSubFormController (String className,
                                                                         Object ... params) {
        try {
            return (ISubFormControllerSprite) Reflection.createInstance(className, params);

        } catch (ReflectionException e){
            
        } catch (ClassCastException e) {
            
        }
        
        return null;
    }

}
