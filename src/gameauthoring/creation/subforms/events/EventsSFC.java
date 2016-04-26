package gameauthoring.creation.subforms.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import engine.definitions.moduledef.ConstantMoverDefinition;
import engine.definitions.moduledef.MovementDefinition;
import engine.definitions.moduledef.StaticMovementDefintion;
import engine.definitions.moduledef.TrackingMoverDefinition;
import engine.definitions.moduledef.UserMoverDefinition;
import gameauthoring.creation.subforms.DynamicSubFormController;
import gameauthoring.creation.subforms.movement.ConstantMoverSFC;
import gameauthoring.creation.subforms.movement.StaticMoverSFC;
import gameauthoring.creation.subforms.movement.TrackingMoverSFC;
import gameauthoring.creation.subforms.movement.UserMoverSFC;

public class EventsSFC extends DynamicSubFormController<EventPackageDefinition>{

    public EventsSFC (IGame game) {
       super(game, new EventSFCFactory(game),
             new ArrayList<String>(Arrays.asList("EVENT", "EFFECT")));
       
    }

    @Override
    protected void changeCurrentSFCBasedOnData (EventPackageDefinition item) {
        item.g
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
