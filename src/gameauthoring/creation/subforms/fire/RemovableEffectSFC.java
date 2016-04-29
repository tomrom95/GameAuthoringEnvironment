package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.EventPackageDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import engine.effects.IEffect;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.MultiOptionSFC;

public abstract class RemovableEffectSFC extends RemovableSFC<EventPackageDefinition>{

    public RemovableEffectSFC (MultiOptionSFC<EventPackageDefinition> sfc) {
        super(sfc);
        // TODO Auto-generated constructor stub
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
