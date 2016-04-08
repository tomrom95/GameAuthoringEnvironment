package engine;

import java.util.Arrays;
import java.util.List;
import engine.sprite.SpriteType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This class implements ISpriteGroup and holds the notion of a sprite group. A sprite groups is a
 * collection of sprites that are deemed to contain the same attributes and behaviors.
 *
 */
public class SpriteGroup implements ISpriteGroup {

    private ObservableList<SpriteType> mySpriteTypes;

    public SpriteGroup (SpriteType ... spriteTypes) {
        this(Arrays.asList(spriteTypes));
    }

    public SpriteGroup (List<SpriteType> spriteTypes) {
        mySpriteTypes = FXCollections.observableArrayList(spriteTypes);
    }

    @Override
    public boolean contains (SpriteType spriteType) {
        return getSpriteTypes().contains(spriteType);
    }

    @Override
    public ObservableList<SpriteType> getSpriteTypes () {
        return mySpriteTypes;
    }

}
