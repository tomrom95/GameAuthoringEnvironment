package engine;

import java.util.List;
import java.util.function.DoublePredicate;
import effects.IEffect;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
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
        myGame.getLevelManager().getCurrentLevel().get().getSprites().stream()
                .filter(sprite -> sprite.get().getType().equals(myGroupToCheck))
                .forEach(sprite -> sprite.get().getAttributes().stream()
                        .filter(atty -> atty.get().getType().equals(myAttributeType))
                        .forEach(atty -> checkAttribute(atty)));
    }

    private void checkAttribute (ObjectProperty<IAttribute> atty) {
        if (myValueCheck.test(atty.get().getValueProperty().get())) {
            myApplyToSelf.forEach(effect -> atty.get().applyEffect(effect));
            myApplyToOtherGroup.forEach(
                                        effect -> myGame.getLevelManager().getCurrentLevel().get()
                                                .getSprites().stream()
                                                .filter(sprite -> sprite.get().getType().equals(
                                                                                                myOtherGroup))
                                                .forEach(sprite -> sprite.get()
                                                        .applyEffect(effect)));
            myApplyToGlobalAttys
                    .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
            myApplyToGlobalAttys.forEach(effect -> myGame.getLevelManager().getCurrentLevel().get()
                    .getAttributeManager().get().applyEffect(effect));
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
