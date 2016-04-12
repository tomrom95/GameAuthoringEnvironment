package engine;

import java.util.List;
import java.util.stream.Collectors;
import engine.definitions.ProfileDefinition;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.TimeDuration;


/**
 * This class is updateable and is responsible to comparing all sprite groups against all of the
 * other sprite groups to check whether collisions have occurred during the time cycle.
 *
 */

public class OnCollisionCondition implements ICondition {

    private IGame myGame;
    private IEventPackage myGroupAPackage;
    private IEventPackage myGroupBPackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnCollisionCondition (IGame game,
                                 IEventPackage groupA,
                                 IEventPackage groupB,
                                 IEventPackage otherPackage,
                                 IEventPackage globalPackage) {

        myGame = game;
        myGroupAPackage = groupA;
        myGroupBPackage = groupB;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;

    }

    /**
     * Compares all sprite groups against other all other sprite groups to check whether
     * any of the collision conditions are met and updates the sprites' effects accordingly
     */
    @Override
    public void update (TimeDuration duration) {
        List<ISprite> sprites =
                myGame.getLevelManager().getCurrentLevel().getSprites();
        for (ISprite outerSprite : sprites.stream()
                .filter(sprite -> myGroupAPackage.getTargetedSpriteGroup()
                        .contains(sprite.getType()))
                .collect(Collectors.toList())) {
            for (ISprite innerSprite : sprites.stream()
                    .filter(sprite -> myGroupBPackage.getTargetedSpriteGroup()
                            .contains(sprite.getType()))
                    .collect(Collectors.toList())) {
                if (outerSprite.getBounds().collide(innerSprite.getBounds())) {
                    handleCollision(outerSprite, innerSprite);
                }
            }
        }
    }

    private void handleCollision (ISprite outerSprite, ISprite innerSprite) {
        applyPackageEffectsToSprite(myGroupAPackage, outerSprite);
        applyPackageEffectsToSprite(myGroupBPackage, innerSprite);
        List<ISprite> sprites =
                myGame.getLevelManager().getCurrentLevel().getSprites();
        sprites.stream()
                // CONCERN here as to what myApplyToOtherGroup is
                .filter(sprite -> myOtherPackage.getTargetedSpriteGroup()
                        .contains(sprite.getType()))
                .forEach(sprite -> myOtherPackage.getMyEffects()
                        .forEach(effect -> sprite.applyEffect(effect)));
        myGlobalPackage.getMyEffects()
                .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
        myGlobalPackage.getMyEffects()
                .forEach(effect -> myGame.getLevelManager().getCurrentLevel()
                        .getAttributeManager().applyEffect(effect));
    }

    private void applyPackageEffectsToSprite (IEventPackage myPackage, ISprite mySprite) {
        myPackage.getMyEffects().forEach(effect -> mySprite.applyEffect(effect));

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
    public ProfileDefinition getProfileDefinition () {
        //TODO
        ProfileDefinition profile = new ProfileDefinition();
        profile.setName("OnColl");
        return profile;
    }

    @Override
    public void setProfileDefinition (ProfileDefinition profileDef) {
        // TODO Auto-generated method stub
        
    }

}
