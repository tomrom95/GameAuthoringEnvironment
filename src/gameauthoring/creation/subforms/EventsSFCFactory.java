package gameauthoring.creation.subforms;

import engine.IGame;
import engine.definitions.EventPackageDefinition;
import gameauthoring.creation.subforms.events.EventsSubFormController;

public class EventsSFCFactory extends SubFormControllerFactory<EventPackageDefinition>{

    public EventsSFCFactory (IGame game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected ISubFormController<EventPackageDefinition> createSubFormController (String type) {
        if (type.equals("Events")) {
            return new EventsSubFormController(getMyGame());
        }
        return null;
    }

}
