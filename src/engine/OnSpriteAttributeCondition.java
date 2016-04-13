package engine;

import java.util.function.DoublePredicate;
import engine.conditions.ICondition;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.profile.IProfile;
import engine.profile.Profile;
import util.TimeDuration;


public class OnSpriteAttributeCondition implements ICondition {

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
        myGame.getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> mySpritePackage.getTargetedSpriteGroup()
                        .contains(sprite.getType()))
                .forEach(sprite -> sprite.getAttributes().stream()
                        .filter(atty -> atty.getType().equals(myAttributeType))
                        .forEach(atty -> checkAttribute(atty)));
    }

    private void checkAttribute (IAttribute atty) {
        if (myValueCheck.test(atty.getValueProperty().get())) {
            mySpritePackage.getMyEffects().forEach(effect -> atty.applyEffect(effect));
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

    @Override
    public IProfile getProfile () {
        return new Profile("On Sprite Attribute Condition", "Acts on sprite attributes", "images/c.png");
    }

    @Override
    public void setProfile (IProfile profile) {
        // TODO Auto-generated method stub
        
    }

}
