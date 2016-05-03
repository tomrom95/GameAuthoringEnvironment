package engine.definitions.spawnerdef;

import java.util.List;
import java.util.stream.Collectors;
import engine.definitions.concrete.IDefinition;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.waves.Wave;
import engine.waves.WaveBlock;
import engine.waves.WaveInfiniteRandom;


/**
 * This class represents a wave definition, which specifies a list of sprite definitions to be
 * spawned in order
 *
 */
public class WaveDefinition implements IDefinition, IProfilable {

    private List<WaveBlockDefinition> myBlocks;
    private IProfile myProfile = new Profile();
    boolean myInfinite;

    public WaveDefinition (List<WaveBlockDefinition> sprites) {
        setListSprites(sprites);
        myInfinite = false;
    }

    public void setListSprites (List<WaveBlockDefinition> sprites) {
        myBlocks = sprites;
    }

    public Wave create () {
        List<WaveBlock> spriteData = myBlocks.stream().map(def -> def.create())
                .collect(Collectors.toList());
        Wave newWave;
        if (myInfinite) {
            newWave = new WaveInfiniteRandom(spriteData);
        }
        else {
            newWave = new Wave(spriteData);
        }
        return newWave;
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;
    }

    public List<WaveBlockDefinition> getWaveBlocks () {
        return myBlocks;
    }

    public void setInfinite (boolean b) {
        myInfinite = b;
    }

    public boolean getInfinite () {
        return myInfinite;
    }

}
