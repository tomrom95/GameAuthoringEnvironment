package engine.conditions;

import java.util.List;
import java.util.stream.Collectors;
import engine.IEventPackage;
import engine.IGame;
import engine.profile.IProfile;
import engine.profile.Profile;
import engine.sprite.ISprite;
import util.TimeDuration;


/**
 * This class is updateable and is responsible to comparing all sprite groups against all of the
 * other sprite groups to check whether collisions have occurred during the time cycle.
 *
 */

public class OnCollisionCondition extends Condition implements ICondition {

    private IGame myGame;
    private IEventPackage myGroupAPackage;
    private IEventPackage myGroupBPackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnCollisionCondition (IGame game,
                                 IEventPackage groupA,
                                 IEventPackage groupB,
                                 IEventPackage otherPackage,
                                 IEventPackage globalPackage) {

        myGame = game;
        myGroupAPackage = groupA;
        myGroupBPackage = groupB;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;

    }

    /**
     * Compares all sprite groups against other all other sprite groups to check whether
     * any of the collision conditions are met and updates the sprites' effects accordingly
     */
    @Override
    public void update (TimeDuration duration) {
        List<ISprite> sprites =
                myGame.getLevelManager().getCurrentLevel().getSprites();
        for (ISprite outerSprite : filterSpriteListWithPackage(sprites, myGroupAPackage)) {
            for (ISprite innerSprite : filterSpriteListWithPackage(sprites, myGroupBPackage)) {
                if (outerSprite.getBounds().collide(innerSprite.getBounds())) {
                    handleCollision(outerSprite, innerSprite);
                }
            }
        }
    }

    private List<ISprite> filterSpriteListWithPackage (List<ISprite> mySprites,
                                                       IEventPackage myFilterPackage) {
        return mySprites.stream().filter(sprite -> myFilterPackage.getTargetedSpriteGroup()
                .contains(sprite.getType())).collect(Collectors.toList());
    }

    private void handleCollision (ISprite outerSprite, ISprite innerSprite) {
        applyPackageToSprite(myGroupAPackage, outerSprite);
        applyPackageToSprite(myGroupBPackage, innerSprite);
        applyOtherAndGlobalEventPackages(myGame, myOtherPackage, myGlobalPackage);
    }


}
