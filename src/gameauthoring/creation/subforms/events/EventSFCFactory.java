package gameauthoring.creation.subforms.events;

import engine.IGame;
import gameauthoring.creation.factories.DynamicSFCFactory;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.ISubFormController;

public class EventSFCFactory extends DynamicSFCFactory <EventPackageDefinition> {

    public EventSFCFactory (IGame game) {
        super(game);
    }

    @Override
    public ISubFormController<EventPackageDefinition> createSubFormController (String type) {
        if (type.equals("EVENT")) {
            return new EventSFC();
        } else if (type.equals("EFFECT")) {
            return new EffectSFC(this.getMyGame());
        }
        return null;
    }

    @Override
    public ISubFormController<EventPackageDefinition> createSubFormController (String type,
                                                                               Object ... params) {
        // TODO Auto-generated method stub
        return null;
    }

}
