package engine;

import java.util.List;
import java.util.function.DoublePredicate;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import util.TimeDuration;


public class OnGlobalAttributeCondition implements ICondition {

    private IGame myGame;
    private AttributeType myAttributeType;
    private DoublePredicate myValueCheck;
    private List<IEffect> myApplyToSelf;
    private ISpriteGroup myOtherGroup;
    private List<IEffect> myApplyToOtherGroup;
    private List<IEffect> myApplyToGlobalAttys;

    public OnGlobalAttributeCondition (IGame game,
                                       AttributeType attributeType,
                                       DoublePredicate valueCheck,
                                       List<IEffect> applyToSelf,
                                       List<IEffect> applyToOtherGroup,
                                       ISpriteGroup otherGroup,
                                       List<IEffect> applyToGlobalAttys) {
        myGame = game;
        myAttributeType = attributeType;
        myValueCheck = valueCheck;
        myApplyToSelf = applyToSelf;
        myOtherGroup = otherGroup;
        myApplyToOtherGroup = applyToOtherGroup;
        myApplyToGlobalAttys = applyToGlobalAttys;
    }

    @Override
    public void update (TimeDuration duration) {
        myGame.getAttributeManager().getAttributes().stream()
                .filter(atty -> atty.get().getType().equals(myAttributeType))
                .forEach(atty -> checkAttribute(atty));
        myGame.getLevelManager().getCurrentLevel().get().getAttributeManager().get().getAttributes()
                .stream()
                .filter(atty -> atty.get().getType().equals(myAttributeType))
                .forEach(atty -> checkAttribute(atty));
    }

    private void checkAttribute (ObjectProperty<IAttribute> atty) {
        if (myValueCheck.test(atty.get().getValueProperty().get())) {
            myApplyToSelf.forEach(effect -> atty.get().applyEffect(effect));
            myApplyToOtherGroup.forEach(
                                        effect -> myGame.getLevelManager().getCurrentLevel().get()
                                                .getSprites().stream()
                                                .filter(sprite -> myOtherGroup.contains(sprite.getType().get()))
                                                .forEach(sprite -> sprite
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
