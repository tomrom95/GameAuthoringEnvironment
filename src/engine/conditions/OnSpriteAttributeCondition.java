//This entire file is part of my masterpiece.
//Jonathan Im

/*
 * This class showcases the use of an anonymous inner class to allow
 * the super class performing the predicate check to in essence call
 * code in the sub class, as defined in the sub class
 * 
 * Also shows how the standard ICondition interface allows interarcting with
 * and measuring very different parts of the game
 */
package engine.conditions;



import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.sprite.ISprite;
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
                        .forEach(attribute -> checkAttribute(attribute, myValueCheck,
                                                             createDoFunction(sprite))));

    }

    private FunctionalDoer createDoFunction (ISprite sprite) {
        return new FunctionalDoer() {
            @Override
            public void doIt () {
                mySpritePackage.getMyEffects().forEach(effect -> sprite.applyEffect(effect));
                mySpritePackage.getMyEvents().forEach(event -> sprite.registerEvent(event));
                applyOtherAndGlobalEventPackages(myGame,
                                                 myOtherPackage,
                                                 myGlobalPackage);
            }
        };
    }


}
