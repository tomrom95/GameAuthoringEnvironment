// This entire file is part of my masterpiece.
// Dhrumil Patel

package engine.conditions;

import java.util.List;
import java.util.stream.Collectors;
import engine.IEventPackage;
import engine.IGame;
import engine.sprite.ISprite;
import util.TimeDuration;


/**
 * This class is updateable and is responsible to comparing all sprite groups against all of the
 * other sprite groups to check whether collisions have occurred during the time cycle.
 *
 * This class serves as one of the many examples of our event package framework in use. In the authoring environment,
 * the user can specify what sprites are grouped together, what effects to apply when to sprite groups collide, and what 
 * events to trigger. The class also demonstrates good usage of class hierarchy and interface usage as we balance the duality 
 * of providing all conditions a few classes to use and implement and also standardizing the notion of a condition to filter through 
 * using the interface. Although I would have liked to refactor this class further, I believe the class is at an optimal in terms of
 * understandability and readablity. The user can use the game authoring GUI to one-to-one map options on the screen and
 * instantiate our back-end, engine notion of conditions. 
 * 
 * Jonathan is responsible for spearheading this framework, but I worked alongside him to refactor our separate notions of events and sprite
 * groups when we decided to add the notion of events, such as death or upgrades to our engine. After the initial implementation, I went 
 * back to add comments and refactor the code. I wanted to showcase this particular condition to showcase our usage of our events package
 * because I recall personally working on this one. 
 * 
 * @author Dhrumil
 * @author Jonathan
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

    /**
     * Provides a list of sprite that all are grouped in the event package
     * @param mySprites list of sprites on screen
     * @param myFilterPackage event package grouping sprites with similar behaviors 
     * @return list of active sprites that share the behaviors of the filter package
     */
    private List<ISprite> filterSpriteListWithPackage (List<ISprite> mySprites,
                                                       IEventPackage myFilterPackage) {
        return mySprites.stream().filter(sprite -> myFilterPackage.getTargetedSpriteGroup()
                .contains(sprite.getType())).collect(Collectors.toList());
    }

    
    /**
     * Applies the required behavior to any groups that are affected upon collision
     * @param outerSprite sprite that applies effect to a sprite group
     * @param innerSprite sprite that applies effect to a sprite group
     */
    private void handleCollision (ISprite outerSprite, ISprite innerSprite) {
        applyPackageToSprite(myGroupAPackage, outerSprite);
        applyPackageToSprite(myGroupBPackage, innerSprite);
        applyOtherAndGlobalEventPackages(myGame, myOtherPackage, myGlobalPackage);
    }

}
