package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.EventPackageDefinition;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;

public abstract class RemovableEffectSFC extends RemovableSFC<EventPackageDefinition>{

    
    public RemovableEffectSFC (MultiOptionSFC<EventPackageDefinition> sfc) {
        super(sfc);
    }


    @Override
    public void removeModule (Object myMod) {
        if (getMyDefinition() != null) {
            if (getMyDefinition().getMyEffectsList().contains(myMod)) {
                getMyDefinition().getMyEffectsList().remove(myMod);
            }
        }
    }


}
