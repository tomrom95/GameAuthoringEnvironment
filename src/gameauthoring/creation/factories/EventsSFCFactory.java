package gameauthoring.creation.factories;

import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.events.EffectChoiceSFC;
import gameauthoring.creation.subforms.events.EventChoiceSFC;

public class EventsSFCFactory extends SubFormControllerFactory<EventPackageDefinition>{

    public EventsSFCFactory (IGame game) {
        super(game);
    }

    @Override
    protected ISubFormController<EventPackageDefinition> createSubFormController (String type, Object ... params) {
        if (type.equals("Events")) {
            return new EventChoiceSFC(getMyGame());
        }else if (type.equals("Effects")){
            return new EffectChoiceSFC(getMyGame());
        }
        throw new ReflectionException("Can't create EventPackage subformcontroller of type " + type);

    }

}
