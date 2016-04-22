package waves;

import java.util.List;
import engine.definitions.SpriteDefinition;
import engine.sprite.ISprite;


public class Wave implements IWave {

    private List<SpriteWaveData> mySprites;

    public Wave (List<SpriteWaveData> sprites) {
       mySprites = sprites;
       
    }

   

	@Override
	public boolean waveCompleted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void spawnSprite(SpriteDefinition s) {
		// TODO Auto-generated method stub
		
	}

}
