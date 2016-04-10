package engine;

import java.util.function.Predicate;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.Coordinate;
import util.TimeDuration;
import engine.sprite.ISprite;


/**
 * This class is updateable and handles click based events and applies effects to the appropriate
 * objects in the game during a specified time cycle
 *
 */

public class OnClickCondition implements ICondition {

    private IGame myGame;
    // private ISpriteGroup myGroupToCheck;
    // private List<IEffect> myApplyToSelf;
    // private ISpriteGroup myOtherGroup;
    // private List<IEffect> myApplyToOtherGroup;
    // private List<IEffect> myApplyToGlobalAttys;

    private IEventPackage mySelfPackage, myOtherPackage, myGlobalPackage;

    public OnClickCondition (IGame game,
                             IEventPackage selfPackage,
                             IEventPackage otherPackage,
                             IEventPackage globalPackage) {

        myGame = game;
        mySelfPackage = selfPackage;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;

        // myGroupToCheck = groupToCheck;
        // myApplyToSelf = applyToSelf;
        // myOtherGroup = otherGroup;
        // myApplyToOtherGroup = applyToOtherGroup;
        // myApplyToGlobalAttys = applyToGlobalAttys;
    }

    @Override
    public void update (TimeDuration duration) {
        // do nothing
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // do nothing
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        Coordinate coord = new Coordinate(mouseEvent.getX(), mouseEvent.getY());
        System.out.println(coord.getX() + " " + coord.getY());
        filterAndHandleSprites(myGame, sprite -> sprite.getBounds().contains(coord));
        // myGame.getLevelManager().getCurrentLevel().get().getSprites().stream()
        // .filter(sprite ->
        // mySelfPackage.getTargetedSpriteGroup().contains(sprite.getType().get()))
        // .filter(sprite -> sprite.getBounds().contains(coord))
        // .forEach(sprite -> handleAction(sprite));
    }

    private void handleAction (ISprite sprite) {
        mySelfPackage.getMyEffects().forEach(effect -> sprite.applyEffect(effect));
        myOtherPackage.getMyEffects()
                .forEach(effect -> myGame.getLevelManager().getCurrentLevel().get().getSprites()
                        .stream()
                        .filter(otherSprite -> myOtherPackage.getTargetedSpriteGroup()
                                .contains(otherSprite.getType().get()))
                        .forEach(otherSprite -> otherSprite.applyEffect(effect)));
        myGlobalPackage.getMyEffects()
                .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
        myGlobalPackage.getMyEffects()
                .forEach(effect -> myGame.getLevelManager().getCurrentLevel().get()
                        .getAttributeManager().get().applyEffect(effect));
    }

    // private void oldFunction(){
    // myApplyToSelf.forEach(effect -> sprite.applyEffect(effect));
    // myApplyToOtherGroup
    // .forEach(effect -> myGame.getLevelManager().getCurrentLevel().get().getSprites()
    // .stream()
    // .filter(otherSprite -> myGroupToCheck.contains(otherSprite.getType().get()))
    // .forEach(otherSprite -> otherSprite.applyEffect(effect)));
    // myApplyToGlobalAttys
    // .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
    // myApplyToGlobalAttys.forEach(effect -> myGame.getLevelManager().getCurrentLevel().get()
    // .getAttributeManager().get().applyEffect(effect));
    // }

    private void filterAndHandleSprites (IGame myGame, Predicate<ISprite> additionalFilter) {
        myGame.getLevelManager().getCurrentLevel().get().getSprites().stream()
                .filter(sprite -> mySelfPackage.getTargetedSpriteGroup()
                        .contains(sprite.getType().get()))
                .filter(additionalFilter)
                .forEach(sprite -> handleAction(sprite));
    }

}
