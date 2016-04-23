package gameauthoring.creation.subforms.movement;

import engine.IGame;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.DynamicSFCFactory;
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
        if (type.equals("SmartAI")) {

            return new SmartAIMovementSubFormController();
        }
        else if (type.equals("Static")) {
            return new StaticMoverSubFormController();

        }
        else if (type.equals("Constant")) {
            return new ConstantMoverSubFormController();

        }
        else if (type.equals("UserMover")) {
            return new UserMoverSubFormController();

        }
        else if (type.equals("Tracking")) {
            return new TrackingMoverSubFormController(getMyGame());

        }

        return null;
    }

}
