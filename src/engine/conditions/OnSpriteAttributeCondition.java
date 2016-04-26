package engine.conditions;

import java.util.function.DoublePredicate;
import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.profile.IProfile;
import engine.profile.Profile;
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

//    private FunctionalDoer createDoFunction () {
//        return new FunctionalDoer() {
//            @Override
//            public void doIt () {
//                applyEventPackageToSprites(myGame,
//                                           mySpritePackage);
//                applyOtherAndGlobalEventPackages(myGame,
//                                                 myOtherPackage,
//                                                 myGlobalPackage);
//            }
//        };
//    }

}
