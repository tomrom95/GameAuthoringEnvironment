package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class implements ISpriteManager and holds a collection of sprites. This class holds the
 * method calls required to add sprites to the collection and handle any behaviors imposed upon its
 * sprites.
 *
 */

public class SpriteManager implements ISpriteManager {

    private ObservableList<ISprite> mySpriteList;
    private List<ISprite> myBufferList;

    public SpriteManager () {
        mySpriteList = FXCollections.observableArrayList();
        myBufferList = new ArrayList<>();
    }

    @Override
    public void update (TimeDuration duration) {
        mySpriteList.addAll(myBufferList);
        myBufferList.clear();
        loopThroughSpritesAndDo(sprite -> sprite.update(duration));
        removeDeadSprites();
        // loopSpritesAndDoIf(sprite -> sprite.shouldBeRemoved(), sprite -> remove(sprite));
    }

    private void removeDeadSprites () {
        getSprites().removeIf(sprite -> sprite.shouldBeRemoved());
    }

    private void loopSpritesAndDoIf (Predicate<ISprite> spriteCondition,
                                     Consumer<ISprite> consumer) {
        getSprites().filtered(sprite -> spriteCondition.test(sprite))
                .forEach(sprite -> consumer.accept(sprite));
    }

    private void loopThroughSpritesAndDo (Consumer<ISprite> consumer) {
        loopSpritesAndDoIf(sprite -> true, consumer);
    }

    @Override
    public void bufferedAdd (ISprite sprite, Coordinate coordinate) {
        sprite.setLocation(coordinate);
        bufferedAdd(sprite);
    }

    @Override
    public ObservableList<? extends Drawable> getDrawables () {
        return mySpriteList;
    }

    @Override
    public void internalizeKeyEvents (List<KeyIOEvent> list) {
        list.forEach(event -> loopThroughSpritesAndDo(sprite -> sprite.registerKeyEvent(event)));

    }

    @Override
    public void internalizeMouseEvents (List<MouseIOEvent> list) {
        list.forEach(event -> loopThroughSpritesAndDo(sprite -> sprite.registerMouseEvent(event)));
    }

    @Override
    public void remove (ISprite sprite) {
        mySpriteList.remove(sprite);
    }

    @Override
    public ObservableList<ISprite> getSprites () {
        return mySpriteList;
    }

    @Override
    public void bufferedAdd (ISprite sprite) {

        myBufferList.add(sprite);
    }

    @Override
    public void add (ISprite sprite, Coordinate coordinate) {
        sprite.setLocation(coordinate);
        add(sprite);
    }

    @Override
    public void add (ISprite sprite) {
        mySpriteList.add(sprite);
    }

}
