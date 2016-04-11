package engine.definitions;

import engine.profile.IProfile;
import engine.profile.Profile;
import graphics.ImageGraphic;


public class ProfileDefinition implements IDefinition {

    private String myName;
    private String myDescription;
    private ImageGraphic myImage;

    public void setImage (ImageGraphic image) {
        myImage = image;
    }

    public ImageGraphic getImage () {
        return myImage;
    }

    public void setName (String name) {
        myName = name;
    }

    public void setDescription (String description) {
        myDescription = description;
    }

    public String getName () {
        return myName;
    }

    public String getDescription () {
        return myDescription;
    }

    public Profile makeProfile () {
        return new Profile(getName(), getDescription(), myImage);
    }

    @Override
    public IProfile getProfile () {
        return new Profile(getName(), getDescription(), myImage);
    }

    @Override
    public void setProfile (IProfile profile) {
        setName(profile.getName());
        setDescription(profile.getDescription());
        setImage(profile.getImage());
    }
}
