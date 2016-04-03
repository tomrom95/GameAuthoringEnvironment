package engine;

import java.util.function.Consumer;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


public class SpriteManager implements ISpriteManager {

    private ObservableList<ObjectProperty<ISprite>> mySpriteList;

    SpriteManager () {
        mySpriteList = FXCollections.observableArrayList();
    }

    @Override
    public void update (TimeDuration duration) {
        loopThroughSpritesAndDo(sprite -> sprite.update(duration));
    }

    private void loopThroughSpritesAndDo (Consumer<ISprite> consumer) {
        for (ObjectProperty<ISprite> sprite : mySpriteList) {
            consumer.accept(sprite.get());
        }
    }

    @Override
    public ObservableList<ObjectProperty<ISprite>> getSprites () {
        return mySpriteList;
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        sprite.getLocation().get().setLocation(coordinate.getX(), coordinate.getY());
    }

}
