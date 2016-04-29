package gameauthoring.creation.subforms.events;

import engine.IGame;
import gameauthoring.creation.factories.DynamicSFCFactory;
import gameauthoring.creation.factories.ReflectionException;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.ISubFormController;

public class EventSFCFactory extends DynamicSFCFactory <EventPackageDefinition> {

    public EventSFCFactory (IGame game) {
        super(game);
    }

    @Override
    public ISubFormController<EventPackageDefinition> createSubFormController (String type, Object ... params) {
        if (type.equals("EVENT")) {
            return new EventSFC();
        } else if (type.equals("EFFECT")) {
            return new EffectSFC(this.getMyGame());
        }
        throw new ReflectionException("Can't create event sub-subformcontroller of type " + type);
    }

}
