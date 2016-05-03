package engine.profile;

public class ProfileDisplay implements IProfilable {

    private IProfile myProfile;

    public ProfileDisplay (String value) {
        myProfile = new Profile(value);
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
