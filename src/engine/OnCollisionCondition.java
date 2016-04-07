package engine;

import java.util.List;
import java.util.stream.Collectors;
import effects.IEffect;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import util.TimeDuration;


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
                    handleCollision(outerSprite, innerSprite);
                }
            }
        }
    }

    private void handleCollision (ISprite outerSprite, ISprite innerSprite) {
        myApplyToA.forEach(effect -> outerSprite.applyEffect(effect));
        myApplyToB.forEach(effect -> innerSprite.applyEffect(effect));
        List<ObjectProperty<ISprite>> sprites =
                myGame.getLevelManager().getCurrentLevel().get().getSprites();
        sprites.stream().map(sprite -> sprite.get())
                .filter(sprite -> sprite.getType().equals(myOtherGroup))
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
