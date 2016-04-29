package gameauthoring.creation.subforms;

import engine.definitions.concrete.EventPackageDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import engine.effects.IEffect;
import engine.events.GameEvent;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.fire.RemovableSFC;

public abstract class MultiOptionFactory<T extends IProfilable> {

    public RemovableSFC<T> createSubFormController (String string) {
        // TODO Auto-generated method stub
        return null;
    }

    public RemovableSFC<SpriteDefinition> createSubFormController (String name,
                                                                   ModuleDefinition firingDef) {
        // TODO Auto-generated method stub
        return null;
    }

    public RemovableSFC<EventPackageDefinition> createSubFormController (String name,
                                                                         IEffect effect) {
        // TODO Auto-generated method stub
        return null;
    }

    public RemovableSFC<EventPackageDefinition> createSubFormController (String name,
                                                                         GameEvent event) {
        // TODO Auto-generated method stub
        return null;
    }

    public RemovableSFC<T> createSubFormController (String name,
                                                                         Object object) {
        // TODO Auto-generated method stub
        return null;
    }

}
