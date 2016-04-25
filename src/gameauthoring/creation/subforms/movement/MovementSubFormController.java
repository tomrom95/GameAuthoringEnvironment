package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.subforms.DynamicSubFormController;
import gameauthoring.creation.subforms.DynamicSubFormView;


/**
 * This subformcontroller is in charge of assigning a movement module to a SpriteDefinition
 * 
 * @author Jeremy Schreck
 *
 */
public class MovementSubFormController extends DynamicSubFormController<SpriteDefinition> {

    /**
     * Constructor
     * 
     * TODO: get strings from xml and use reflection in factory classes
     * 
     * @param game The current game object
     */
    public MovementSubFormController (IGame game) {
        super(game, new MovementSFCFactory(game),
              new ArrayList<String>(Arrays.asList("STATIC", "CONSTANT", "USERMOVER", "TRACKING")));
        
    }


}
