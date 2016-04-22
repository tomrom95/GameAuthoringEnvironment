package waves;

import java.util.List;
import engine.definitions.SpriteDefinition;

public class Wave implements IWave {

	private List<SpriteWaveData> mySprites;
	private int totalSprites;

	public Wave(List<SpriteWaveData> sprites) {
		totalSprites = 0;
		mySprites = sprites;
		mySprites.stream().forEach(p -> totalSprites += p.getCount());
	}

	@Override
	public boolean waveCompleted() {
		return totalSprites == 0;
	}

	@Override
	public boolean spawnSprite(SpriteDefinition s) {

		for (SpriteWaveData data : mySprites) {
			if (data.getDefinition().equals(s)) {
				data.setCount(data.getCount() - 1);
				if (data.getCount() < 0) {
					return false;
				} else {
					totalSprites--;
					return true;
				}

			}
			
		}
		return false;
	}

}
