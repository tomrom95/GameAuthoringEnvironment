package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.StaticMover;
import engine.profile.IProfile;


public class StaticMoverDefinition extends MovementDefinition {

    private IProfile myProfile;

    @Override
    public IMovementModule create (IPositionable parent) {
        return new StaticMover(parent);
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;
    }

}
