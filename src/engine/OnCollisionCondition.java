package engine;

import java.util.List;
import java.util.stream.Collectors;
import effects.IEffect;
import javafx.beans.property.ObjectProperty;
import util.TimeDuration;


/**
 * This class is updateable and is responsible to comparing all sprite groups against all of the
 * other sprite groups to check whether collisions have occurred during the time cycle.
 *
 */

public class OnCollisionCondition implements ICondition {

    private IGame myGame;
    private ISpriteGroup myGroupA;
    private ISpriteGroup myGroupB;
    private List<IEffect> myApplyToA;
    private List<IEffect> myApplyToB;

    public OnCollisionCondition (IGame game,
                                 ISpriteGroup groupA,
                                 ISpriteGroup groupB,
                                 List<IEffect> applyToA,
                                 List<IEffect> applyToB) {
        myGame = game;
        myGroupA = groupA;
        myGroupB = groupB;
        myApplyToA = applyToA;
        myApplyToB = applyToB;
    }

    /**
     * Compares all sprite groups against other all other sprite groups to check whether
     * any of the collision conditions are met and updates the sprites' effects accordingly
     */
    @Override
    public void update (TimeDuration duration) {
        List<ObjectProperty<ISprite>> sprites =
                myGame.getLevelManager().getCurrentLevel().get().getSprites();
        for (ISprite outerSprite : sprites.stream()
                .map(p -> p.get())
                .filter(sprite -> sprite.getType().equals(myGroupA))
                .collect(Collectors.toList())) {
            for (ISprite innerSprite : sprites.stream()
                    .map(p -> p.get())
                    .filter(sprite -> sprite.getType().equals(myGroupB))
                    .collect(Collectors.toList())) {
                if (outerSprite.getBounds().collide(innerSprite.getBounds())) {
                    myApplyToA.forEach(effect -> outerSprite.applyEffect(effect));
                    myApplyToB.forEach(effect -> innerSprite.applyEffect(effect));
                }
            }
        }
    }

}
