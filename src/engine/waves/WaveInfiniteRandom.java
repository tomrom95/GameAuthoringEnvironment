package engine.waves;

import java.util.ArrayList;
import java.util.List;
import engine.sprite.ISprite;
import util.TimeDuration;


public class WaveInfiniteRandom extends Wave implements IWave {

    private static final int RANDOM_TIME_MULTIPLIER = 2;
    List<Integer> myWeightedIndices;

    public WaveInfiniteRandom (List<WaveBlock> sprites) {
        super(sprites);
        getBlocks().add(getCurrentBlock());
        myWeightedIndices = createIndexArray();

    }

    @Override
    public boolean waveCompleted () {
        return false;
    }

    @Override
    public boolean satisfiedSpawnInterval (TimeDuration t) {
        getTimeSinceSpawn().increase(t);

        if (getTimeSinceSpawn().getMillis() >= getCurrentBlock().getGap().getMillis() *
                                               Math.random() *
                                               RANDOM_TIME_MULTIPLIER) {
            getTimeSinceSpawn().setToZero();
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public ISprite spawnSprite () {
        WaveBlock myHeldCurrent = getCurrentBlock();
        setCurrentBlock(getRandomCurrentBlock());
        return myHeldCurrent.spawnInfinite();
    }

    private WaveBlock getRandomCurrentBlock () {
        return getBlocks().get(myWeightedIndices.get(Math
                .round(new Double(myWeightedIndices.size() * Math.random()).floatValue())));

    }

    private List<Integer> createIndexArray () {
        List<Integer> indexList = new ArrayList<Integer>();
        List<WaveBlock> blocks = getBlocks();
        int dex = 0;
        for (WaveBlock block : blocks) {
            addIndicesToList(indexList, block, dex);
            dex++;
        }
        return indexList;

    }

    private void addIndicesToList (List<Integer> list, WaveBlock b, int index) {
        for (int i = 0; i < b.getCount(); i++) {
            list.add(index);
        }
    }

}
