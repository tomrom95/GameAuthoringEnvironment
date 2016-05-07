//This entire file is part of my masterpiece.
//Jonathan Im

/*
 * This class illustrates how the default behavior abstract super class
 * allows us to achieve extremely clean code, also an example of responding
 * to user input through overriding a Condition super method
 */
package engine.conditions;

import java.util.function.Predicate;
import engine.IEventPackage;
import engine.IGame;
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

}
