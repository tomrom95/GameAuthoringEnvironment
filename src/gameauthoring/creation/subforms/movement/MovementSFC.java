package gameauthoring.creation.subforms.movement;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import engine.definitions.moduledef.MovementDefinition;
import engine.definitions.moduledef.PathMoverDefinition;
import engine.definitions.moduledef.StaticMovementDefintion;
import engine.definitions.moduledef.TrackingMoverDefinition;
import engine.definitions.moduledef.UserMoverDefinition;
import gameauthoring.creation.factories.MovementSFCFactory;
import gameauthoring.creation.subforms.dynamic.DynamicSubFormController;

/**
 * A MovementSFC is a SubFormController in charge of assigning a movement module to a SpriteDefinition
 * 
 * @author Jeremy Schreck
 *
 */
public class MovementSFC extends DynamicSubFormController<SpriteDefinition> {
    private static String MY_MOVEMENT_KEY= "Movement";

    /**
     * Constructs a MovementSFC with the given game object, a MovementSFCFactory
     * and a list of strings specifying which movement options to display
     * 
     * 
     * @param game The current game object
     */
    public MovementSFC (IGame game) {
        super(game, new MovementSFCFactory(game), MY_MOVEMENT_KEY);
        
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
            
        }else if (movDef instanceof PathMoverDefinition){
            this.setMyCurrentSFC(new PathMoverSFC());
            
        }
        
    }


}
