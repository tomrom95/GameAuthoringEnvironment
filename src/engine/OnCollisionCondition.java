package engine;

import java.util.List;
import java.util.stream.Collectors;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
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
    private List<IEffect> myApplyToOtherGroup;
    private ISpriteGroup myOtherGroup;
    private List<IEffect> myApplyToGlobalAttys;

    public OnCollisionCondition (IGame game,
                                 ISpriteGroup groupA,
                                 ISpriteGroup groupB,
                                 List<IEffect> applyToA,
                                 List<IEffect> applyToB,
                                 List<IEffect> applyToOtherGroup,
                                 ISpriteGroup otherGroup,
                                 List<IEffect> applyToGlobalAttys) {
        myGame = game;
        myGroupA = groupA;
        myGroupB = groupB;
        myApplyToA = applyToA;
        myApplyToB = applyToB;
        myApplyToOtherGroup = applyToOtherGroup;
        myOtherGroup = otherGroup;
        myApplyToGlobalAttys = applyToGlobalAttys;
    }

    /**
     * Compares all sprite groups against other all other sprite groups to check whether
     * any of the collision conditions are met and updates the sprites' effects accordingly
     */
    @Override
    public void update (TimeDuration duration) {
        List<ISprite> sprites =
                myGame.getLevelManager().getCurrentLevel().get().getSprites();
        for (ISprite outerSprite : sprites.stream()
                
                .filter(sprite -> myGroupA.contains(sprite.getType().get()))
                .collect(Collectors.toList())) {
            for (ISprite innerSprite : sprites.stream()
                    .filter(sprite -> myGroupB.contains(sprite.getType().get()))
                    .collect(Collectors.toList())) {
                if (outerSprite.getBounds().collide(innerSprite.getBounds())) {
                    handleCollision(outerSprite, innerSprite);
                }
            }
        }
    }

    private void handleCollision (ISprite outerSprite, ISprite innerSprite) {
        myApplyToA.forEach(effect -> outerSprite.applyEffect(effect));
        myApplyToB.forEach(effect -> innerSprite.applyEffect(effect));
        List<ISprite> sprites =
                myGame.getLevelManager().getCurrentLevel().get().getSprites();
        sprites.stream()
                .filter(sprite -> myOtherGroup.contains(sprite.getType().get()))
                .forEach(sprite -> myApplyToOtherGroup
                        .forEach(effect -> sprite.applyEffect(effect)));
        myApplyToGlobalAttys.forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
        myApplyToGlobalAttys.forEach(effect -> myGame.getLevelManager().getCurrentLevel().get()
                .getAttributeManager().get().applyEffect(effect));
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // do nothing

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // do nothing

    }

}
