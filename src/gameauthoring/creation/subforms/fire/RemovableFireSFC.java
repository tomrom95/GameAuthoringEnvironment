package gameauthoring.creation.subforms.fire;

import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import gameauthoring.creation.subforms.dynamic.MultiSFController;


public abstract class RemovableFireSFC extends RemovableSFC<SpriteDefinition> {

    public RemovableFireSFC (MultiSFController<SpriteDefinition> sfc) {
        super(sfc);
    }

    @Override
    public void removeModule (Object myMod) {
        if (getMyDefinition() != null) {
            if (getMyDefinition().getModuleDefinitions().contains(myMod)) {
                getMyDefinition().remove((ModuleDefinition) myMod);
            }
        }
    }

}
