package gameauthoring.creation.subforms.events;

import java.util.List;
import java.util.function.Consumer;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormView;

public class EventsSubFormView extends DynamicSubFormView{ 

    public EventsSubFormView (List<ISubFormView> subforms,
                              Consumer<Integer> changeSelectionAction,
                              List<String> options) {
        super(subforms, changeSelectionAction, options);
    }

    @Override
    protected String getSelectionKey () {
        return "Event or Effect: ";
    }

}
