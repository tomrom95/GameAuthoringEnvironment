package engine.conditions;

import java.util.function.DoublePredicate;
import java.util.stream.Stream;
import engine.IAttribute;
import engine.IEventPackage;
import engine.IGame;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.sprite.ISprite;
import util.TimeDuration;


/**
 * This abstract class represents all types of conditions, and defines some of behavior that they
 * all use to apply effects to specified locations
 * 
 * @author Jonathan Im
 * @author David Maydew
 *
 */
public abstract class Condition implements ICondition {

    IProfile myProfile = new Profile();

    /**
     * By default, do nothing in response
     */
    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
    }

    /**
     * By default, do nothing in response
     */
    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    /**
     * By default, do nothing in response
     */
    @Override
    public void update (TimeDuration duration) {
    }

    /**
     * Applies a given package to a third-party group of sprites
     * 
     * @param game containing current sprites
     * @param toApply event package to apply to sprites
     */
    protected void applyEventPackageToSprites (IGame game,
                                               IEventPackage toApply) {
        toApply.getMyEffects()
                .forEach(effect -> getPackageFilteredSprites(game, toApply)
                        .forEach(otherSprite -> otherSprite.applyEffect(effect.makeCopy())));
        toApply.getMyEvents()
                .forEach(event -> getPackageFilteredSprites(game, toApply)
                        .forEach(otherSprite -> otherSprite.registerEvent(event)));
    }

    /**
     * Applies a package to a third-party group of sprites and a different package to all global
     * attributes
     * 
     * @param game to apply the packages into
     * @param otherPackage event package to apply to sprites
     * @param globalPackage event package to apply to global attributes
     */
    protected void applyOtherAndGlobalEventPackages (IGame game,
                                                     IEventPackage otherPackage,
                                                     IEventPackage globalPackage) {
        applyEventPackageToSprites(game, otherPackage);
        globalPackage.getMyEffects()
                .forEach(effect -> game.getAttributeManager().applyEffect(effect));
        globalPackage.getMyEvents()
                .forEach(event -> game.getAttributeManager().registerEvent(event));

        globalPackage.getMyEffects()
                .forEach(effect -> game.getLevelManager().getCurrentLevel()
                        .getAttributeManager().applyEffect(effect.makeCopy()));
        globalPackage.getMyEvents()
                .forEach(event -> game.getLevelManager().getCurrentLevel()
                        .getAttributeManager().registerEvent(event));
        game.getLevelManager().getCurrentLevel().getNextLevelManager()
                .internalizeGameEvents(globalPackage.getMyEvents());
    }

    /**
     * @param game to be executed on
     * @param filterPackage specifying the group of sprites
     * @return A stream of ISprites containing only ones in the specified Group
     */
    protected Stream<ISprite> getPackageFilteredSprites (IGame game, IEventPackage filterPackage) {
        return game.getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> filterPackage.getTargetedSpriteGroup()
                        .contains(sprite.getType()));
    }

    /**
     * Applies a given package to a sprite
     * 
     * @param myPackage to apply
     * @param mySprite to apply to
     */
    protected void applyPackageToSprite (IEventPackage myPackage, ISprite mySprite) {
        myPackage.getMyEffects().forEach(effect -> mySprite.applyEffect(effect));
        myPackage.getMyEvents().forEach(event -> mySprite.registerEvent(event));

    }

    /**
     * Checks the value of an attribute with a given predicate, and executes a function if the test
     * is matched
     * 
     * @param attribute to check
     * @param valueCheck predicate specifying a boolean test method
     * @param doExecute to run if check is true
     */
    protected void checkAttribute (IAttribute attribute,
                                   DoublePredicate valueCheck,
                                   FunctionalDoer doExecute) {
        if (valueCheck.test(attribute.getValueProperty().get())) {
            doExecute.doIt();
        }
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;
    }

}
