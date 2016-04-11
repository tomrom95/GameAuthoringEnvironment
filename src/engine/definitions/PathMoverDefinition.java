package engine.definitions;

import java.util.ArrayList;
import java.util.List;
import engine.IPositionable;
import engine.modules.IMovementModule;
import engine.modules.PathMover;
import engine.profile.IProfile;
import util.Coordinate;


public class PathMoverDefinition extends MovementDefinition {

    private List<Coordinate> myList;
    private IProfile myProfile;

    public PathMoverDefinition () {
        myList = new ArrayList<>();
    }

    @Override
    public IMovementModule create (IPositionable parent) {
        return new PathMover(getSpeed(), new ArrayList<Coordinate>(myList), parent);
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
