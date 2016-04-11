package engine.definitions;

import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.profile.IProfile;


public abstract class MovementDefinition implements IDefinition {

    private double mySpeed;
    private IProfile myProfile;

    public abstract IMovementModule create (IPositionable parent);

    public void setSpeed (double speed) {
        mySpeed = speed;
    }

    protected double getSpeed () {
        return mySpeed;
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
