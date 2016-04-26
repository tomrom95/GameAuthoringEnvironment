package gameauthoring.creation.subforms.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import engine.definitions.moduledef.MovementDefinition;
import engine.definitions.moduledef.StaticMovementDefintion;
import engine.definitions.moduledef.TrackingMoverDefinition;
import engine.definitions.moduledef.UserMoverDefinition;
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

    @Override
    protected void changeCurrentSFCBasedOnData (SpriteDefinition item) {
        MovementDefinition movDef = item.getMovementDefinition();
        
        //TODO fix bad code or move to factory
        if (movDef instanceof StaticMovementDefintion){
            this.setMyCurrentSFC(new StaticMoverSFC());
            
        }else if (movDef instanceof ConstantMoverDefinition){
            this.setMyCurrentSFC(new ConstantMoverSFC());
            
        }else if (movDef instanceof UserMoverDefinition){
            this.setMyCurrentSFC(new UserMoverSFC());
            
        }else if (movDef instanceof TrackingMoverDefinition){
            this.setMyCurrentSFC(new TrackingMoverSFC(getMyGame()));
            
        }
        
    }


}
