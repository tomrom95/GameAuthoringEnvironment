package engine.definitions.spawnerdef;

import java.util.List;
import java.util.stream.Collectors;
import engine.definitions.concrete.IDefinition;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.waves.WaveBlock;
import engine.waves.Wave;


/**
 * This class represents a wave definition, which specifies a list of sprite definitions to be
 * spawned in order
 *
 */
public class WaveDefinition implements IDefinition, IProfilable {
    
    private List<WaveBlockDefinition> myBlocks;
    private Profile myProfile = new Profile();

    public WaveDefinition (List<WaveBlockDefinition> sprites) {
    	setListSprites(sprites);
    }

    public void setListSprites (List<WaveBlockDefinition> sprites) {
        myBlocks = sprites;
    }

    public Wave create(){
        List<WaveBlock> spriteData = myBlocks.stream().map(def -> def.create())
                .collect(Collectors.toList());
    	return new Wave(spriteData);
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
