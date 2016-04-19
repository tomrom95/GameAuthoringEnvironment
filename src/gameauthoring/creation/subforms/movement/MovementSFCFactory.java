package gameauthoring.creation.subforms.movement;

import engine.IGame;
import gameauthoring.creation.subforms.DynamicSFCFactory;
import gameauthoring.creation.subforms.ISubFormControllerSprite;


public class MovementSFCFactory extends DynamicSFCFactory {

    public MovementSFCFactory (IGame game) {
        super(game);

    }

    public ISubFormControllerSprite createSpriteSubFormController (String type) {
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
