package engine;

import java.util.function.Predicate;
import engine.definitions.ProfileDefinition;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class is updateable and handles click based events and applies effects to the appropriate
 * objects in the game during a specified time cycle
 *
 * This code will pass events to all sprites that are under the click
 *
 */

public class OnClickCondition implements ICondition {

    private IGame myGame;
    private IEventPackage mySelfPackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnClickCondition (IGame game,
                             IEventPackage selfPackage,
                             IEventPackage otherPackage,
                             IEventPackage globalPackage) {

        myGame = game;
        mySelfPackage = selfPackage;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;
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
    }

    private void handleAction (ISprite sprite) {
        mySelfPackage.getMyEffects().forEach(effect -> sprite.applyEffect(effect));
        myOtherPackage.getMyEffects()
                .forEach(effect -> myGame.getLevelManager().getCurrentLevel().getSprites()
                        .stream()
                        .filter(otherSprite -> myOtherPackage.getTargetedSpriteGroup()
                                .contains(otherSprite.getType()))
                        .forEach(otherSprite -> otherSprite.applyEffect(effect)));
        myGlobalPackage.getMyEffects()
                .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));

        myGlobalPackage.getMyEffects()
                .forEach(effect -> myGame.getLevelManager().getCurrentLevel()
                        .getAttributeManager().applyEffect(effect));
    }

    private void filterAndHandleSprites (IGame game, Predicate<ISprite> additionalFilter) {
        game.getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> mySelfPackage.getTargetedSpriteGroup()
                        .contains(sprite.getType()))
                .filter(additionalFilter)
                .forEach(sprite -> handleAction(sprite));

    }

    @Override
    public ProfileDefinition getProfileDefinition () {
        //TODO
        ProfileDefinition profile = new ProfileDefinition();
        profile.setName("OnClick");
        return profile;
    }

    @Override
    public void setProfileDefinition (ProfileDefinition profileDef) {
        // TODO Auto-generated method stub
        
    }

}
