package engine;

import java.util.List;
import java.util.function.DoublePredicate;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.TimeDuration;


public class OnSpriteAttributeCondition implements ICondition {

    private IGame myGame;
    private ISpriteGroup myGroupToCheck;
    private AttributeType myAttributeType;
    private DoublePredicate myValueCheck;
    private List<IEffect> myApplyToSelf;
    private ISpriteGroup myOtherGroup;
    private List<IEffect> myApplyToOtherGroup;
    private List<IEffect> myApplyToGlobalAttys;

    public OnSpriteAttributeCondition (IGame game,
                                       ISpriteGroup groupToCheck,
                                       AttributeType attributeType,
                                       DoublePredicate valueCheck,
                                       List<IEffect> applyToSelf,
                                       List<IEffect> applyToOtherGroup,
                                       ISpriteGroup otherGroup,
                                       List<IEffect> applyToGlobalAttys) {
        myGame = game;
        myGroupToCheck = groupToCheck;
        myAttributeType = attributeType;
        myValueCheck = valueCheck;
        myApplyToSelf = applyToSelf;
        myOtherGroup = otherGroup;
        myApplyToOtherGroup = applyToOtherGroup;
        myApplyToGlobalAttys = applyToGlobalAttys;
    }

    @Override
    public void update (TimeDuration duration) {

        myGame.getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> myGroupToCheck.contains(sprite.getType()))
                .forEach(sprite -> sprite.getAttributes().stream()
                        .filter(atty -> atty.getType().equals(myAttributeType))
                        .forEach(atty -> checkAttribute(atty)));
    }

    private void checkAttribute (IAttribute atty) {
        if (myValueCheck.test(atty.getValueProperty().get())) {
            myApplyToSelf.forEach(effect -> atty.applyEffect(effect));
            myApplyToOtherGroup.forEach(
                                        effect -> myGame.getLevelManager().getCurrentLevel()
                                                .getSprites().stream()
                                                .filter(sprite -> myOtherGroup
                                                        .contains(sprite.getType()))
                                                .forEach(sprite -> sprite
                                                        .applyEffect(effect)));
            myApplyToGlobalAttys
                    .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
            myApplyToGlobalAttys.forEach(effect -> myGame.getLevelManager().getCurrentLevel()
                    .getAttributeManager().applyEffect(effect));
        }
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
