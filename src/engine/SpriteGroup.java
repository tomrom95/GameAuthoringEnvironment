package engine;

import java.util.Arrays;
import java.util.List;
import engine.sprite.SpriteType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpriteGroup implements ISpriteGroup {
    
    private ObservableList<SpriteType> mySpriteTypes;
    
    public SpriteGroup (SpriteType... spriteTypes) {
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
