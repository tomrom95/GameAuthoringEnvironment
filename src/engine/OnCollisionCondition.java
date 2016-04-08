package engine;

import java.util.List;
import java.util.stream.Collectors;
import engine.effects.IEffect;
import engine.sprite.ISprite;
import javafx.beans.property.ObjectProperty;
import util.TimeDuration;


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

    @Override
    public void update (TimeDuration duration) {
        List<ISprite> sprites =
                myGame.getLevelManager().getCurrentLevel().get().getSprites();
        for (ISprite outerSprite : sprites.stream()
                
                .filter(sprite -> sprite.getType().equals(myGroupA))
                .collect(Collectors.toList())) {
            for (ISprite innerSprite : sprites.stream()
                    
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
