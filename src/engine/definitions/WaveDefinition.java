package engine.definitions;

import java.util.List;
import engine.modules.Wave;
import engine.profile.IProfile;


public class WaveDefinition implements IDefinition {

    private List<SpriteDefinition> mySprites;
    private IProfile myProfile;

    public WaveDefinition (List<SpriteDefinition> sprites) {
        setListSprites(sprites);
    }

    public void setListSprites (List<SpriteDefinition> sprites) {
        mySprites = sprites;
    }

    public Wave create () {
        return new Wave(mySprites);
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
