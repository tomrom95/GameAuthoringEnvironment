package engine;

import java.util.List;

public interface IModule {

    void applyEffect(IEffect effect);
    
    List<IEffect> getResultingEffects();
}
