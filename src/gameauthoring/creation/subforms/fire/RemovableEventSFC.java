package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;

public abstract class RemovableEventSFC extends RemovableSFC<EventPackageDefinition>{

    public RemovableEventSFC (MultiOptionSFC<EventPackageDefinition> sfc) {
        super(sfc);
    }

    @Override
    public void removeModule (Object myMod) {
        if (getMyDefinition() != null) {
            if (getMyDefinition().getMyEventsList().contains(myMod)) {
                getMyDefinition().getMyEventsList().remove(myMod);
            }
        }
    }

}
