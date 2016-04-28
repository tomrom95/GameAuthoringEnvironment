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
        
        
        //TODO fix bad code or move to factory
        if(!item.getMyEffectsList().isEmpty()){
            //effect
            this.setMyCurrentSFC(new EffectSFC(getMyGame()));
        }else if (!item.getMyEventsList().isEmpty()){
            //event
            this.setMyCurrentSFC(new EventSFC());
        }
       
    }
    
    @Override
    public void updateItem(EventPackageDefinition item) {
        item.getMyEffectsList().clear();
        item.getMyEventsList().clear();
        
        super.updateItem(item);
    }

}
