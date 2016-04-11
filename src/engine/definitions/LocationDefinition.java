package engine.definitions;

import engine.profile.IProfile;
import util.Coordinate;


public class LocationDefinition implements IDefinition {

    private double myX;
    private double myY;
    private IProfile myProfile;

    public Coordinate create () {
        return new Coordinate(myX, myY);
    }

    public void setX (int x) {
        myX = x;
    }

    public void setY (int y) {
        myY = y;
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
