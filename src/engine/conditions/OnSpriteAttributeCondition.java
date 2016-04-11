package engine.conditions;

import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IAttribute;
import engine.IEventPackage;
import engine.IGame;
import util.TimeDuration;


public class OnSpriteAttributeCondition extends Condition implements ICondition {

    private IGame myGame;
    private AttributeType myAttributeType;
    private DoublePredicate myValueCheck;
    private IEventPackage mySpritePackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnSpriteAttributeCondition (IGame game,
                                       AttributeType attributeType,
                                       DoublePredicate valueCheck,
                                       IEventPackage spritePackage,
                                       IEventPackage otherPackage,
                                       IEventPackage globalPackage) {
        myGame = game;
        myAttributeType = attributeType;
        myValueCheck = valueCheck;
        mySpritePackage = spritePackage;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;

    }

    @Override
    public void update (TimeDuration duration) {
        getPackageFilteredSprites(myGame, mySpritePackage)
            .forEach(sprite -> sprite.getAttributes().stream()
                        .filter(atty -> atty.getType().equals(myAttributeType))
                        .forEach(atty -> checkAttribute(atty)));
    }

    private void checkAttribute (IAttribute atty) {
        if (myValueCheck.test(atty.getValueProperty().get())) {
            applyEventPackageToSprites(myGame, mySpritePackage);
            applyOtherAndGlobalEventPackages(myGame, myOtherPackage, myGlobalPackage);
        }
    }


}
