package gameauthoring.creation.factories;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.movement.ConstantMoverSFC;
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
        throw new ReflectionException("Can't create movement sub-subformcontroller of type " + type);

    }

}
