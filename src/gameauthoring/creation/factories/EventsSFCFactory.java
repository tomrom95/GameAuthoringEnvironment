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
    protected ISubFormController<EventPackageDefinition> createSubFormController (String type, Object ... params) {
        if (type.equals("Events")) {
            return new EventsSFC(getMyGame());
        }
        throw new ReflectionException("Can't create EventPackage subformcontroller of type " + type);

    }

}
