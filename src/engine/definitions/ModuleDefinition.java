package engine.definitions;

import engine.modules.IModule;
import engine.profile.IProfile;


public abstract class ModuleDefinition implements IDefinition {
    
    private IProfile myProfile;
    
    public abstract IModule create ();
    
    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;

    }
}
