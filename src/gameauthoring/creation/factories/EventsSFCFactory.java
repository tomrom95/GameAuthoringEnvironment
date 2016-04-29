package gameauthoring.creation.factories;

import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.events.EventsSFC;

public class EventsSFCFactory extends SubFormControllerFactory<EventPackageDefinition>{

    public EventsSFCFactory (IGame game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected ISubFormController<EventPackageDefinition> createSubFormController (String type) {
        if (type.equals("Events")) {
            return new EventsSFC(getMyGame());
        }
        return null;
    }

}
