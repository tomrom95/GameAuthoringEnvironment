package engine.conditions;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import engine.IEventPackage;
import engine.IGame;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.Bounds;
import util.Coordinate;
import util.TimeDuration;

/**
 * Condition that firers when a sprite goes out the current level bounds
 * @author RyanStPierre
 *
 */

public class OutOfBoundsCondition extends Condition {

    private IGame myGame;
    private IEventPackage mySelfPackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OutOfBoundsCondition (IGame game,
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
        List<ISprite> sprites =
                myGame.getLevelManager().getCurrentLevel().getSprites();
        Bounds levelBounds = myGame.getLevelManager().getCurrentLevel().getBounds();
        for (ISprite sprite : filterSpriteListWithPackage(sprites, mySelfPackage)) {
            if (!sprite.getBounds().collide(levelBounds)) {
                handleOutOfBounds(sprite);
            }
        }
    }

    private List<ISprite> filterSpriteListWithPackage (List<ISprite> mySprites,
                                                       IEventPackage myFilterPackage) {
        return mySprites.stream().filter(sprite -> myFilterPackage.getTargetedSpriteGroup()
                .contains(sprite.getType())).collect(Collectors.toList());
    }

    private void handleOutOfBounds (ISprite sprite) {
        applyPackageToSprite(mySelfPackage, sprite);
        applyOtherAndGlobalEventPackages(myGame, myOtherPackage, myGlobalPackage);
    }

}
