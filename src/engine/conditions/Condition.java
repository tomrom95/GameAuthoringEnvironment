// This entire file is part of my masterpiece.
// David Maydew
// I have chosen this class to be a part of my masterpiece because it represents the highest level
// of a Bridge design pattern, which in this case is used to differentiate the different trigger
// mechanisms for the different conditions we implement. As this is a high-level, abstract class, it
// features heavy commenting and concise methods common across its sub-classes. Since these
// subclasses have different behavior, this class is closed and extensible, allowing for its
// beneficiaries to take advantage of its methods to implement their specific behavior. This class
// also manifests some syntactic sugar with the heavy use of Java Streams (functional programming)
// to make the code more readable and modern.
package engine.conditions;

import java.util.stream.Stream;
import engine.Affectable;
import engine.IEventPackage;
import engine.IGame;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.sprite.ISprite;
import util.TimeDuration;


/**
 * This abstract class represents the skeleton for all types of conditions,
 * and defines some of behavior that they all share to apply {@link engine.effects.IEffect}s and
 * {@link engine.events.EventPackage}s to specified locations. By default, it has no response to
 * events specified {@link engine.IOAffectable} and should be extended as necessary
 *
 * @author Jonathan Im
 * @author David Maydew
 *
 */
public abstract class Condition implements ICondition {

    private IProfile myProfile = new Profile();

    /**
     * By default, do nothing in response to an {@link engine.interactionevents.IOEvent} being
     * registered
     */
    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
    }

    /**
     * By default, do nothing in response to an {@link engine.interactionevents.IOEvent} being
     * registered
     */
    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    /**
     * By default, do nothing in response to an update cycle
     */
    @Override
    public void update (TimeDuration duration) {
    }

    /**
     * Applies a given {@link engine.events.EventPackage} to the active sprites in a game targeted
     * by that EventPackage
     *
     * @param game containing current sprites
     * @param toApply event package to apply to sprites
     */
    protected void applyEventPackageToSprites (IGame game,
                                               IEventPackage toApply) {
        getPackageFilteredSprites(game, toApply)
                .forEach(sprite -> applyEventsFromPackage(toApply, sprite));
    }

    /**
     * Applies a given {@link engine.events.EventPackage} to a third-party group of sprites and a
     * different package to all global attributes (level-wide and game-wide)
     *
     * @param game to apply the packages into
     * @param otherGroupPackage event package to apply to sprites
     * @param globalPackage event package to apply to global attributes
     */
    protected void applyOtherAndGlobalEventPackages (IGame game,
                                                     IEventPackage otherGroupPackage,
                                                     IEventPackage globalPackage) {
        applyEventPackageToSprites(game, otherGroupPackage);

        applyEventsFromPackage(globalPackage, game.getAttributeManager());
        applyEventsFromPackage(globalPackage,
                               game.getLevelManager().getCurrentLevel().getAttributeManager());
    }

    /**
     * @param game to be executed on
     * @param filterPackage specifying the group of sprites
     * @return A stream of ISprites containing only ones in the specified Group
     */
    protected Stream<ISprite> getPackageFilteredSprites (IGame game, IEventPackage filterPackage) {
        return game.getLevelManager().getCurrentLevel().getSprites()
                .stream()
                .filter(sprite -> filterPackage.getTargetedSpriteGroup()
                        .contains(sprite.getType()));
    }

    /**
     * Applies the effects and events in a given {@link engine.events.EventPackage} to an
     * {@link engine.Affectable} recipient
     * 
     * @param eventPackage to apply
     * @param recipient to apply to
     */
    private void applyEventsFromPackage (IEventPackage eventPackage, Affectable recipient) {
        eventPackage.getMyEffects().forEach(effect -> recipient.applyEffect(effect.makeCopy()));
        eventPackage.getMyEvents().forEach(event -> recipient.registerEvent(event));
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
