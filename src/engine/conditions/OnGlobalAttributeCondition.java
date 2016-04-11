package engine.conditions;

import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IAttribute;
import engine.IAttributeManager;
import engine.IEventPackage;
import engine.IGame;
import util.TimeDuration;


public class OnGlobalAttributeCondition extends Condition implements ICondition {

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
            applyOtherAndGlobalEventPackages(myGame, myOtherPackage, myGlobalPackage);
        }
    }

}
