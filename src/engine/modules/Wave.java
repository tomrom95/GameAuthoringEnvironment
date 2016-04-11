package engine.modules;

import java.util.List;
import engine.definitions.SpriteDefinition;
import engine.sprite.ISprite;


public class Wave implements IWave {

    private List<SpriteDefinition> mySprites;
    private int myCurrent;

    public Wave (List<SpriteDefinition> sprites) {
        myCurrent = 0;
        mySprites = sprites;
    }

    @Override
    public ISprite getNextSprite () {
        ISprite sprite = mySprites.get(myCurrent).create();
        myCurrent++;
        return sprite;

    }

}
