package gameauthoring.creation.subforms.events;

import engine.IGame;
import gameauthoring.creation.factories.Reflection;
import gameauthoring.creation.factories.ReflectionException;
import engine.definitions.concrete.EventPackageDefinition;
import engine.effects.IEffect;
import engine.events.GameEvent;
import gameauthoring.creation.subforms.MultiOptionFactory;
import gameauthoring.creation.subforms.fire.RemovableSFC;
import gameauthoring.util.ErrorMessage;

public class EventSFCFactory extends MultiOptionFactory <EventPackageDefinition> {

    private EventChoiceSFC myEventSFC;
    private EffectChoiceSFC myEffectSFC;
    
    public EventSFCFactory (IGame game, EventChoiceSFC eventSFC, EffectChoiceSFC effectSFC) {
        super(game);
        myEffectSFC = effectSFC;
        myEventSFC = eventSFC;
    }

    @Override

    public RemovableSFC<EventPackageDefinition> createSubFormController (String className, Object ...params ) {

        try {
            return (RemovableSFC<EventPackageDefinition>) Reflection.createInstance(className, params);

        }
        catch (ReflectionException | ClassCastException e) {
            String errorMsg = "Check your properties files. Unable to create firing subformcontroller with className " + className;
            System.out.println(errorMsg);
            ErrorMessage errorMessage = new ErrorMessage(errorMsg);
            errorMessage.showError();
            throw new ReflectionException("Can't create event sub-subformcontroller of type " + className);
        }
        
        /*
        if (type.equals("EVENT")) {
            return new EventSFC();
        } else if (type.equals("EFFECT")) {
            return new EffectSFC(this.getMyGame());
        }
        */
       

    }
    public RemovableSFC<EventPackageDefinition> createSubFormController (String name,
                                                                         IEffect effect) {
        return this.createSubFormController(name, effect);
    }

    public RemovableSFC<EventPackageDefinition> createSubFormController (String name,
                                                                         GameEvent event) {
        return this.createSubFormController(name, event);
    }

}
