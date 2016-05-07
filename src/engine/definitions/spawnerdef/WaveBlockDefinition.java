package engine.definitions.spawnerdef;

import engine.definitions.concrete.SpriteDefinition;
import engine.waves.WaveBlock;


public class WaveBlockDefinition {

    private SpriteDefinition mySpawnedSprite;
    private int myCount;
    private double myGapTime;

    public WaveBlockDefinition () {
        myCount = 0;
        myGapTime = 0;
    }

    public WaveBlockDefinition (SpriteDefinition def, int count, double gap) {
        mySpawnedSprite = def;
        setCount(count);
        setGap(gap);
    }

    private void setGap (double gap) {
        myGapTime = gap;
    }

    public double getGap () {
        return myGapTime;
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

    public WaveBlock create () {
        return new WaveBlock(mySpawnedSprite, myCount, myGapTime);
    }

}
