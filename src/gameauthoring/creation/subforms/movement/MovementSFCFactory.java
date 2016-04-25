package gameauthoring.creation.subforms.movement;

import engine.IGame;

import gameauthoring.creation.factories.DynamicSFCFactory;
import engine.definitions.concrete.SpriteDefinition;

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
        if (type.equals("SMARTAI")) {

            return new SmartAIMovementSubFormController();
        }
        else if (type.equals("STATIC")) {
            return new StaticMoverSubFormController();

        }
        else if (type.equals("CONSTANT")) {
            return new ConstantMoverSubFormController();

        }
        else if (type.equals("USERMOVER")) {
            return new UserMoverSubFormController();

        }
        else if (type.equals("TRACKING")) {
            return new TrackingMoverSubFormController(getMyGame());

        }

        return null;
    }

}
