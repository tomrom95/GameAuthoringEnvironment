package gameauthoring.creation.subforms.events;

import engine.IGame;
import gameauthoring.creation.factories.DynamicSFCFactory;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.MultiOptionFactory;
import gameauthoring.creation.subforms.fire.RemovableSFC;

public class EventSFCFactory extends MultiOptionFactory <EventPackageDefinition> {

    public EventSFCFactory (IGame game, Event ) {
        super(game);
    }

    @Override
    public RemovableSFC<EventPackageDefinition> createSubFormController (String type) {
        if (type.equals("EVENT")) {
            return new EventSFC();
        } else if (type.equals("EFFECT")) {
            return new EffectSFC(this.getMyGame());
        }
        return null;
    }

    @Override
    public RemovableSFC<EventPackageDefinition> createSubFormController (String type,
                                                                               Object ... params) {
        // TODO Auto-generated method stub
        return null;
    }

}
