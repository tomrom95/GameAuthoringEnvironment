package engine.definitions.spawnerdef;

import engine.definitions.concrete.SpriteDefinition;
import engine.waves.SpriteWaveData;


public class WaveDataDefinition {

    private SpriteDefinition mySpawnedSprite;
    private int myCount;

    public WaveDataDefinition () {
        myCount = 0;
    }

    public WaveDataDefinition (SpriteDefinition def, int count) {
        mySpawnedSprite = def;
        setCount(count);
    }

    public void setCount (int count) {
        myCount = count;
    }

    public int getCount () {
        return myCount;
    }

    public void setSprite (SpriteDefinition sprite) {
        mySpawnedSprite = sprite;
    }

    public SpriteDefinition getSprite () {
        return mySpawnedSprite;
    }

    public SpriteWaveData create () {
        return new SpriteWaveData(mySpawnedSprite, myCount);
    }

}
