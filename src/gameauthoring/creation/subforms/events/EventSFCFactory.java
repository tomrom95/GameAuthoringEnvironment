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
        if (type.equals("Event")) {
            return new EventSubFormController();
        } else if (type.equals("Effect")) {
            return new EffectSubFormController(this.getMyGame());
        }
        return null;
    }

}
