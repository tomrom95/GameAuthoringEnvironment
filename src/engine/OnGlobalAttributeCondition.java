package engine;

import java.util.function.DoublePredicate;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import util.TimeDuration;


public class OnGlobalAttributeCondition implements ICondition {

    private IGame myGame;
    private AttributeType myAttributeType;
    private DoublePredicate myValueCheck;

    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnGlobalAttributeCondition (IGame game,
                                       AttributeType attributeType,
                                       DoublePredicate valueCheck,
                                       IEventPackage otherPackage,
                                       IEventPackage globalPackage) {
        myGame = game;
        myAttributeType = attributeType;
        myValueCheck = valueCheck;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;
    }

    @Override
    public void update (TimeDuration duration) {
        checkAttributes(duration, myGame.getAttributeManager());
        checkAttributes(duration, myGame.getLevelManager().getCurrentLevel().getAttributeManager());
    }

    private void checkAttributes (TimeDuration duration, IAttributeManager myManager) {
        myManager.getAttributes().stream()
                .filter(atty -> atty.getType().equals(myAttributeType))
                .forEach(atty -> checkAttribute(atty));
    }

    private void checkAttribute (IAttribute atty) {
        if (myValueCheck.test(atty.getValueProperty().get())) {
            myGlobalPackage.getMyEffects().forEach(effect -> atty.applyEffect(effect));
            myOtherPackage.getMyEffects().forEach(
                                                  effect -> myGame.getLevelManager()
                                                          .getCurrentLevel()
                                                          .getSprites().stream()
                                                          .filter(sprite -> myOtherPackage
                                                                  .getTargetedSpriteGroup()
                                                                  .contains(sprite.getType()))

                                                          .forEach(sprite -> sprite
                                                                  .applyEffect(effect)));
            myGlobalPackage.getMyEffects()
                    .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
            myGlobalPackage.getMyEffects()
                    .forEach(effect -> myGame.getLevelManager().getCurrentLevel()
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
