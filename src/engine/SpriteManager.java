package engine;

import java.util.List;
import java.util.function.Consumer;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
        getSprites().forEach(sprite -> consumer.accept(sprite.get()));
    }

    @Override
    public ObservableList<ObjectProperty<ISprite>> getSprites () {
        return mySpriteList;
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        mySpriteList.add(new SimpleObjectProperty<>(sprite));
        sprite.getLocation().get().setLocation(coordinate.getX(), coordinate.getY());
    }
    
    @Override
    public ObservableList<? extends ObjectProperty<? extends Drawable>> getDrawables () {
        return mySpriteList;
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        list.forEach(event -> loopThroughSpritesAndDo(sprite-> sprite.registerKeyEvent(event)));
        
    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        list.forEach(event -> loopThroughSpritesAndDo(sprite-> sprite.registerMouseEvent(event)));
    }

}
