package gameauthoring.creation.subforms.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.DynamicSubFormController;

public class EventsSubFormController extends DynamicSubFormController<EventPackageDefinition>{

    public EventsSubFormController (IGame game) {
       super(game, new EventSFCFactory(game),
             new ArrayList<String>(Arrays.asList("EVENT", "EFFECT")));
       
    }

}
