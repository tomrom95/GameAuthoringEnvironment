package engine.conditions;

import java.util.function.DoublePredicate;
import java.util.stream.Stream;
import engine.IAttribute;
import engine.IEventPackage;
import engine.IGame;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.TimeDuration;


public abstract class Condition implements ICondition {

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
    }

    @Override
    public void update (TimeDuration duration) {
    }

    protected void applyEventPackageToSprites (IGame game,
                                               IEventPackage toApply) {
        toApply.getMyEffects()
                .forEach(effect -> getPackageFilteredSprites(game, toApply)
                        .forEach(otherSprite -> otherSprite.applyEffect(effect.makeCopy())));
        toApply.getMyEvents()
                .forEach(event -> getPackageFilteredSprites(game, toApply)
                        .forEach(otherSprite -> otherSprite.registerEvent(event)));
    }

    protected void applyOtherAndGlobalEventPackages (IGame game,
                                                     IEventPackage otherPackage,
                                                     IEventPackage globalPackage) {
        applyEventPackageToSprites(game, otherPackage);
        globalPackage.getMyEffects()
                .forEach(effect -> game.getAttributeManager().applyEffect(effect));
        globalPackage.getMyEvents()
                .forEach(event -> game.getAttributeManager().registerEvent(event));

        globalPackage.getMyEffects()
                .forEach(effect -> game.getLevelManager().getCurrentLevel()
                        .getAttributeManager().applyEffect(effect.makeCopy()));
        globalPackage.getMyEvents()
                .forEach(event -> game.getLevelManager().getCurrentLevel()
                        .getAttributeManager().registerEvent(event));
    }

    protected Stream<ISprite> getPackageFilteredSprites (IGame game, IEventPackage filterPackage) {
        return game.getLevelManager().getCurrentLevel().getSprites().stream()
                .filter(sprite -> filterPackage.getTargetedSpriteGroup()
                        .contains(sprite.getType()));
    }

    protected void applyPackageToSprite (IEventPackage myPackage, ISprite mySprite) {
        myPackage.getMyEffects().forEach(effect -> mySprite.applyEffect(effect));
        myPackage.getMyEvents().forEach(event -> mySprite.registerEvent(event));

    }

    protected void checkAttribute (IAttribute attribute,
                                   DoublePredicate valueCheck,
                                   FunctionalDoer myDo) {
        if (valueCheck.test(attribute.getValueProperty().get())) {
            myDo.doIt();
        }
    }

}
