package engine.waves;

import java.util.List;
import engine.sprite.ISprite;
import util.TimeDuration;


public class Wave implements IWave {

    private List<WaveBlock> myBlocks;
    private WaveBlock currentBlock;
    private TimeDuration sinceLastSpawn;

    public Wave (List<WaveBlock> sprites) {
        myBlocks = sprites;
        currentBlock =  myBlocks.remove(0);
        sinceLastSpawn = new TimeDuration();
        sinceLastSpawn.setToZero();
    }

    @Override
    public boolean waveCompleted () {
        return myBlocks.size() == 0 && currentBlock.getCount() <= 0;
    }



    @Override
    public ISprite spawnSprite () {
        if (isBlockEmpty() && !myBlocks.isEmpty()) {
            currentBlock = myBlocks.get(0);
            myBlocks.remove(0);
        }
        
        return currentBlock.spawn();

    }

    private boolean isBlockEmpty () {
        return currentBlock.isComplete();
    }

    @Override
    public boolean satisfiedSpawnInterval (TimeDuration t) {
        sinceLastSpawn.increase(t);

        if (sinceLastSpawn.getMillis() >= currentBlock.getGap().getMillis()) {
            sinceLastSpawn.setToZero();
            return true;
        }
        else {
            return false;
        }

    }

}
