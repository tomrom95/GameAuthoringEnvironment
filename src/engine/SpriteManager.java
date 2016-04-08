package engine;

import java.util.List;
import java.util.function.Consumer;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


public class SpriteManager implements ISpriteManager {

    private ObservableList<ISprite> mySpriteList;

    public SpriteManager () {
        mySpriteList = FXCollections.observableArrayList();
    }

    @Override
    public void update (TimeDuration duration) {
        loopThroughSpritesAndDo(sprite -> sprite.update(duration));
    }

    private void loopThroughSpritesAndDo (Consumer<ISprite> consumer) {
        getSprites().forEach(sprite -> consumer.accept(sprite));
    }

   
    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        mySpriteList.add(sprite);
        sprite.getLocation().get().setLocation(coordinate.getX(), coordinate.getY());
    }
    
    @Override
    public ObservableList<? extends Drawable> getDrawables () {
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

    @Override
    public void remove (ObjectProperty<ISprite> sprite) {
        mySpriteList.remove(sprite);
    }

    @Override
    public ObservableList<ISprite> getSprites () {
        return mySpriteList;
    }

}
