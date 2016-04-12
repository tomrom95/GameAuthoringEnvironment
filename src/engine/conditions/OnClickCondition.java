package engine.conditions;

import java.util.function.Predicate;
<<<<<<< HEAD:src/engine/OnClickCondition.java
import engine.definitions.ProfileDefinition;
import engine.interactionevents.KeyIOEvent;
=======
import engine.IEventPackage;
import engine.IGame;
>>>>>>> 5c956e7a2a513d379853c626b1bd2924f5d9bd91:src/engine/conditions/OnClickCondition.java
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.Coordinate;


/**
 * This class is updateable and handles click based events and applies effects to the appropriate
 * objects in the game during a specified time cycle
 *
 * This code will pass events to all sprites that are under the click
 *
 */

public class OnClickCondition extends Condition implements ICondition {

    private IGame myGame;
    private IEventPackage mySelfPackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnClickCondition (IGame game,
                             IEventPackage selfPackage,
                             IEventPackage otherPackage,
                             IEventPackage globalPackage) {

        myGame = game;
        mySelfPackage = selfPackage;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {

        Coordinate coord = new Coordinate(mouseEvent.getX(), mouseEvent.getY());
        System.out.println(coord.getX() + " " + coord.getY());
        filterAndHandleSprites(myGame, sprite -> sprite.getBounds().contains(coord));
    }

    private void handleAction (ISprite sprite) {
        applyPackageToSprite(mySelfPackage, sprite);
        applyOtherAndGlobalEventPackages(myGame, myOtherPackage, myGlobalPackage);
    }

    private void filterAndHandleSprites (IGame game, Predicate<ISprite> additionalFilter) {
        getPackageFilteredSprites(game, mySelfPackage)
                .filter(additionalFilter)
                .forEach(sprite -> handleAction(sprite));

    }

    @Override
    public ProfileDefinition getProfileDefinition () {
        //TODO
        ProfileDefinition profile = new ProfileDefinition();
        profile.setName("OnClick");
        return profile;
    }

    @Override
    public void setProfileDefinition (ProfileDefinition profileDef) {
        // TODO Auto-generated method stub
        
    }

}
